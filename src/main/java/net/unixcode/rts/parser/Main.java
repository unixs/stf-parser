package net.unixcode.rts.parser;

import net.unixcode.rts.parser.api.*;
import net.unixcode.rts.parser.services.DefaultParserRunner;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.*;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

@Service("main")
public class Main {
  public static void main(String @NotNull [] argv) throws Exception {
    if (argv.length < 1) {
      throw new Exception("Input file not provided.");
    }

    ApplicationContext ctxt = new AnnotationConfigApplicationContext(DIConfig.class);

    DefaultParserRunner parserRunner = (DefaultParserRunner) ctxt.getBean("default_parser_runner");

    parserRunner.setArgv(argv);

    var parserThread = new Thread(parserRunner, "Main parsing thread");

    parserThread.start();
    parserThread.join();
  }

  static ParserType parserTypeProviderImpl(String[] argv) {
    return ParserType.XML;
  }

  static @NotNull List<String> argvGetFileNames(List<String> argv) {
    var result = new ArrayList<String>();

    try {
      if (argv.size() < 2) {
        throw new Exception("The file name is not specified in cmd.");
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
    }
    catch (Exception e) {
      System.err.println(e.getLocalizedMessage());
      e.printStackTrace(System.err);
      System.exit(1);
    }

    return result;
  }

  @Configuration
  // @ImportResource(locations = {"classpath:spring/app_context.xml"})
  @ComponentScan(basePackages = {"net.unixcode.rts.parser"})
  static class DIConfig {
    @Bean
    IParserTypeProvider parser_type_provider() {
      return Main::parserTypeProviderImpl;
    }
    @Bean
    IFileNamesProvider file_names_provider() {
      return Main::argvGetFileNames;
    }
  }
}
