package net.unixcode.rts.parser.services;

import net.unixcode.rts.parser.api.IInputStreamsProvider;
import net.unixcode.rts.parser.api.IParserRunner;
import org.antlr.v4.runtime.CharStream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service("default_parser_runner")
public class DefaultParserRunner implements IParserRunner {
  protected List<String> argv = null;
  protected IInputStreamsProvider streamsProvider;

  @Autowired
  public DefaultParserRunner(IInputStreamsProvider streamsProvider) {
    this.streamsProvider = streamsProvider;
  }

  public void setArgv(String[] argv) {
    this.argv = Arrays.asList(argv);
  }

  @Override
  public void run() {
    System.out.println("Parser is running.");

    if (argv == null) {
      System.err.println("ARGV not set for DefaultParserRunner");
      System.exit(1);
    }

    for (CharStream stream : this.streamsProvider.setArgv(argv)) {
      System.out.println(stream);
    }
  }
}
