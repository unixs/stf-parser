package net.unixcode.rts.parser;

import net.sf.saxon.s9api.Processor;
import net.sf.saxon.s9api.XsltCompiler;
import net.unixcode.rts.parser.antlr.stf.stfLexer;
import net.unixcode.rts.parser.antlr.stf.stfParser;
import net.unixcode.rts.parser.api.*;
import net.unixcode.rts.parser.api.compiler.CompilerType;
import net.unixcode.rts.parser.api.compiler.ICompilerTypeProvider;
import net.unixcode.rts.parser.api.compiler.ISTF2XMLTypeMapper;
import net.unixcode.rts.parser.api.compiler.antlr.stf.*;
import net.unixcode.rts.parser.api.compiler.xml.*;
import net.unixcode.rts.parser.compiler.antlr.stf.STFSourceItem;
import net.unixcode.rts.parser.compiler.xml.settings.CabinXMLSettings;
import net.unixcode.rts.parser.compiler.xml.DefaultXMLTransformer;
import net.unixcode.rts.parser.compiler.xml.settings.SoundXMLSettings;
import net.unixcode.rts.parser.compiler.xml.settings.StateXMLSettings;
import net.unixcode.rts.parser.compiler.xml.XMLSourceItem;
import org.apache.commons.io.FilenameUtils;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.*;

import javax.xml.transform.stream.StreamSource;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

public class Main {
  final static protected Logger log = LoggerFactory.getLogger(Main.class);
  public static void main(String @NotNull [] argv) {
    try {
      ApplicationContext ctxt = new AnnotationConfigApplicationContext(DIConfig.class);

      IThreadsPoolProvider poolProvider = ctxt.getBean(IThreadsPoolProvider.class);

      ICompilerRunner runner = ctxt.getBean(ICompilerRunner.class);

      runner.setSources(argv);

      poolProvider.forEach(executor -> executor.execute(runner));
    }
    catch (Exception e) {
      log.error(e.getMessage());
      System.exit(1);
    }
  }

  static XMLType parserTypeProviderImpl(String[] argv) {
    return XMLType.CABIN;
  }

  @Contract("_ -> new")
  static @NotNull List<String> argvCheckFileNames(@NotNull List<String> argv) {
    if (argv.isEmpty()) {
      log.error("The file names are not specified in cmd.");
      System.exit(1);
    }

    return new ArrayList<>(argv);
  }

  @Configuration
  @Lazy
  // @ImportResource(locations = {"classpath:spring/context.xml"})
  @ComponentScan(basePackages = { "net.unixcode.rts.parser" })
  @PropertySource("classpath:application.properties")
  static class DIConfig {
    final static protected Logger log = LoggerFactory.getLogger(Main.class);
    @Bean
    IParserTypeProvider parser_type_provider() {
      return Main::parserTypeProviderImpl;
    }

    @Bean
    IFileNamesProvider file_names_provider() {
      return Main::argvCheckFileNames;
    }

    @Bean
    ISTFLexerSupplier stf_lexer_supplier() {
      return stfLexer::new;
    }

    @Bean
    ISTFParserSupplier stf_parser_supplier() {
      return stfParser::new;
    }

    @Bean
    ISTFSourceItemFactory stf_source_item_factory(ISTFTypeProvider typeProvider) {
      return (srcPath) -> STFSourceItem.factory(typeProvider, srcPath);
    }

    @Bean
    IXMLSourceItemFactory xml_source_item_factory(IXMLTypeProvider typeProvider) {
      return (srcPath) -> XMLSourceItem.factory(typeProvider, srcPath);
    }

    @Bean
    ICompilerTypeProvider compiler_type_provider() {
      return (ext) -> switch (ext) {
        case "xml" -> CompilerType.XML;
        case "sd", "sms", "eng", "rtseng" -> CompilerType.STF;
        default -> throw new IllegalArgumentException(
          MessageFormat.format("Unprocessable file type [.{0}]", ext)
        );
      };
    }

    @Bean
    ISTFTypeProvider stf_type_provider() {
      return (sourceItem) -> {
        var ext = FilenameUtils.getExtension(sourceItem.getSourcePath());

        return switch (ext) {
          case "sd" -> STFType.SD;
          case "sms" -> STFType.SMS;
          default -> STFType.UNKNOWN;
        };
      };
    }

    @Bean
    IXMLTypeMapper xml_type_mapper() {
      return (ns) -> {
        if (ns == null) {
          return XMLType.UNKNOWN;
        }

        return switch (ns) {
          case "http://rts.unixcode.net/xml/cabin/model/1.0.0" -> XMLType.CABIN;
          case "http://rts.unixcode.net/xml/cabin/state/1.0.0" -> XMLType.STATE;
          case "http://rts.unixcode.net/xml/sound/1.0.0" -> XMLType.SOUND;
          default -> XMLType.UNKNOWN;
        };
      };
    }

    @Bean
    IXMLTransformer cabin_xml_transformer(ApplicationContext appContext, @NotNull CabinXMLSettings xmlSettings) {
      return new DefaultXMLTransformer(
        appContext,
        xslt_transformer_supplier(),
        xmlSettings.getXslt()
      );
    }

    @Bean
    IXMLTransformer state_xml_transformer(ApplicationContext appContext, @NotNull StateXMLSettings xmlSettings) {
      return new DefaultXMLTransformer(
        appContext,
        xslt_transformer_supplier(),
        xmlSettings.getXslt()
      );
    }

    @Bean
    IXMLTransformer sound_xml_transformer(ApplicationContext appContext, @NotNull SoundXMLSettings xmlSettings) {
      return new DefaultXMLTransformer(
        appContext,
        xslt_transformer_supplier(),
        xmlSettings.getXslt()
      );
    }

    @Bean
    IXMLTransformerProvider xml_transformer_provider(ApplicationContext applicationContext, @NotNull StateXMLSettings stateSettings, @NotNull CabinXMLSettings cabinSettings, @NotNull SoundXMLSettings soundSettings) {
      return (xmlType) -> switch (xmlType) {
        case CABIN -> cabin_xml_transformer(applicationContext, cabinSettings);
        case STATE -> state_xml_transformer(applicationContext, stateSettings);
        case SOUND -> sound_xml_transformer(applicationContext, soundSettings);
        default -> throw new IllegalArgumentException("There is no XML transformer for ["+ xmlType +"] type/namespace.");
      };
    }

    @Bean
    ISTF2XMLTypeMapper stf_xml_mapper() {
      return (stfType) -> switch (stfType) {
        case SD -> XMLType.CABIN;
        case SMS -> XMLType.SOUND;
        default -> XMLType.UNKNOWN;
      };
    }

    @Bean
    IXsltTransformerSupplier xslt_transformer_supplier() {
      return (xsltInputStream) -> {
        try {
          var processor = new Processor(false);
          XsltCompiler compiler = processor.newXsltCompiler();

          var stylesheet = compiler.compile(new StreamSource(xsltInputStream));

          return stylesheet.load30();
        }
        catch (Exception e) {
          log.error("Unable to initialize XSLT transformer.");
          log.error(e.getMessage());

          throw new RuntimeException(e);
        }
      };
    }
  }
}
