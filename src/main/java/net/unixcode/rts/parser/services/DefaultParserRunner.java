package net.unixcode.rts.parser.services;

import net.unixcode.rts.parser.api.BaseParserExecutor;
import net.unixcode.rts.parser.api.IInputStreamsProvider;
import net.unixcode.rts.parser.api.IParserRunner;
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.Parser;
import org.antlr.v4.runtime.tree.ParseTreeListener;

import java.util.Arrays;
import java.util.List;

public abstract class DefaultParserRunner<L extends Lexer, P extends Parser> implements IParserRunner {
  protected List<String> argv = null;
  protected IInputStreamsProvider streamsProvider;
  protected BaseParserExecutor<L, P> parserExecutor;
  protected ParseTreeListener listener;

  public DefaultParserRunner(IInputStreamsProvider streamsProvider, BaseParserExecutor<L, P> parserExecutor, ParseTreeListener listener) {
    this.streamsProvider = streamsProvider;
    this.parserExecutor = parserExecutor;
    this.listener = listener;
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

    var streams = this.streamsProvider.apply(argv);

    for (var stream : streams) {
      this.parserExecutor.apply(stream);
      this.parserExecutor.exec(listener);

      System.out.println(listener.toString());
    }
  }
}
