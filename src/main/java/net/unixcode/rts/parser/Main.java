package net.unixcode.rts.parser;

import net.sf.saxon.s9api.Processor;
import net.sf.saxon.s9api.XsltCompiler;
import net.unixcode.rts.parser.antlr.stf.stfLexer;
import net.unixcode.rts.parser.antlr.stf.stfParser;
import net.unixcode.rts.parser.api.*;
import net.unixcode.rts.parser.api.compiler.CompilerType;
import net.unixcode.rts.parser.api.compiler.ICompilerTypeProvider;
import net.unixcode.rts.parser.api.compiler.antlr.stf.ISTFLexerSupplier;
import net.unixcode.rts.parser.api.compiler.antlr.stf.ISTFParserSupplier;
import net.unixcode.rts.parser.api.compiler.antlr.stf.ISTFSourceItemFactory;
import net.unixcode.rts.parser.api.compiler.xml.IXMLSourceItemFactory;
import net.unixcode.rts.parser.api.compiler.xml.IXMLTransformer;
import net.unixcode.rts.parser.api.compiler.xml.IXsltTransformerSupplier;
import net.unixcode.rts.parser.api.compiler.xml.ParserType;
import net.unixcode.rts.parser.translator.DefaultSourceItem;
import net.unixcode.rts.parser.translator.antlr.ANTLRSourceItem;
import net.unixcode.rts.parser.translator.xml.DefaultXMLTranslator;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
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

  static ParserType parserTypeProviderImpl(String[] argv) {
    return ParserType.MODEL;
  }

  static @NotNull List<String> argvCheckFileNames(List<String> argv) {
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
    ISTFSourceItemFactory stf_source_item_factory() {
      return ANTLRSourceItem::stfSourceItemFactory;
    }

    @Bean
    IXMLSourceItemFactory xml_source_item_factory() {
      return DefaultSourceItem::defaultSourceItemFactory;
    }

    @Bean
    ICompilerTypeProvider compiler_type_provider() {
      return (ext) -> switch (ext) {
        case "xml" -> CompilerType.XML;
        case "sd", "sms", "eng", "rtseng" -> CompilerType.STF;
        default -> throw new IllegalArgumentException(MessageFormat.format("Unprocessable file type [.{0}]", ext));
      };
    }

    @Bean
    IXMLTransformer model_xml_transformer(ApplicationContext applicationContext, @Value("${xml.cabin.xslt}") String xsltPath) {
      return new DefaultXMLTranslator(
        applicationContext,
        xslt_transformer_supplier(),
        xsltPath
      );
    }

    @Bean
    IXMLTransformer state_xml_transformer(ApplicationContext applicationContext, @Value("${xml.state.xslt}") String xsltPath) {
      return new DefaultXMLTranslator(
        applicationContext,
        xslt_transformer_supplier(),
        xsltPath
      );
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
