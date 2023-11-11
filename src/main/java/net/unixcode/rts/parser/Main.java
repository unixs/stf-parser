package net.unixcode.rts.parser;

import net.sf.saxon.s9api.Processor;
import net.sf.saxon.s9api.XsltCompiler;
import net.unixcode.rts.parser.antlr.stf.stfLexer;
import net.unixcode.rts.parser.antlr.stf.stfParser;
import net.unixcode.rts.parser.api.*;
import net.unixcode.rts.parser.api.stf.ISTFLexerSupplier;
import net.unixcode.rts.parser.api.stf.ISTFParserSupplier;
import net.unixcode.rts.parser.parsers.stf.STFRunnerProvider;
import net.unixcode.rts.parser.translator.DefaultXMLTranslator;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.*;
import org.springframework.stereotype.Component;

import javax.xml.transform.stream.StreamSource;
import java.util.ArrayList;
import java.util.List;

@Component("main")
public class Main {
  final static protected Logger log = LoggerFactory.getLogger(Main.class);
  public static void main(String @NotNull [] argv) {
    if (argv.length < 1) {
      log.error("No input files were provided.");

      System.exit(1);
    }

    ApplicationContext ctxt = new AnnotationConfigApplicationContext(DIConfig.class);

    IThreadsPoolProvider poolProvider = ctxt.getBean(IThreadsPoolProvider.class);

    IParserRunnerProvider parserRunnerProvider = ctxt.getBean(STFRunnerProvider.class);

    parserRunnerProvider.prepare(argv);

    poolProvider.forEach(executor -> {
      IParserRunner runner = parserRunnerProvider.get();
      executor.execute(runner);
    });
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
