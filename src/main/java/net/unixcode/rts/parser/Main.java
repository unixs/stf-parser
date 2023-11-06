package net.unixcode.rts.parser;

import net.unixcode.rts.parser.antlr.stf.stfLexer;
import net.unixcode.rts.parser.antlr.stf.stfParser;
import net.unixcode.rts.parser.api.*;
import net.unixcode.rts.parser.api.stf.ISTFLexerSupplier;
import net.unixcode.rts.parser.api.stf.ISTFParserSupplier;
import net.unixcode.rts.parser.parsers.stf.STFRunnerProvider;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.*;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service("main")
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
    return ParserType.XML;
  }

  static @NotNull List<String> argvCheckFileNames(List<String> argv) {
    if (argv.isEmpty()) {
      log.error("The file names are not specified in cmd.");
      System.exit(1);
    }

    return new ArrayList<>(argv);
  }

  @Configuration
  // @ImportResource(locations = {"classpath:spring/app_context.xml"})
  @ComponentScan(basePackages = { "net.unixcode.rts.parser" })
  @PropertySource("classpath:application.properties")
  static class DIConfig {
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

    @Bean()
    ISTFParserSupplier stf_parser_supplier() {
      return stfParser::new;
    }
  }
}
