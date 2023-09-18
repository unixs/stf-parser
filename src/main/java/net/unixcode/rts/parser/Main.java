package net.unixcode.rts.parser;

import net.unixcode.rts.parser.antlr.stf.stfLexer;
import net.unixcode.rts.parser.antlr.stf.stfParser;
import net.unixcode.rts.parser.api.*;
import net.unixcode.rts.parser.api.stf.ISTFLexerSupplier;
import net.unixcode.rts.parser.api.stf.ISTFParserSupplier;
import net.unixcode.rts.parser.parsers.stf.STFRunnerProvider;
import org.jetbrains.annotations.NotNull;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.*;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service("main")
public class Main {
  public static void main(String @NotNull [] argv) throws Exception {
    if (argv.length < 1) {
      throw new Exception("Input file not provided.");
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

  static @NotNull List<String> argvGetFileNames(List<String> argv) {
    var result = new ArrayList<String>();

    try {
      if (argv.size() < 2) {
        System.err.println("The file names are not specified in cmd.");
        System.exit(1);
      }
      else {
        result = argv.stream().reduce(result, (a, b, c) -> {
          
        };
      }



    for (var path : argv) {

      var file = new File(path);

      if (!file.exists()) {
        throw new Exception("File is not exists.");
      }

      if (!file.isFile()) {
        throw new Exception("Specified path is not a file.");
      }

      if (!file.canRead()) {
        throw new Exception("Specified file is not readable.");
      }

      result.add(file.getCanonicalPath());
    }



    return result;
  }

  @Configuration
  // @ImportResource(locations = {"classpath:spring/app_context.xml"})
  @ComponentScan(basePackages = { "net.unixcode.rts.parser" })
  static class DIConfig {
    @Bean
    IParserTypeProvider parser_type_provider() {
      return Main::parserTypeProviderImpl;
    }

    @Bean
    IFileNamesProvider file_names_provider() {
      return Main::argvGetFileNames;
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
