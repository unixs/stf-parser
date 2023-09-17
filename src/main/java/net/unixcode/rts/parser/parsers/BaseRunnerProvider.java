package net.unixcode.rts.parser.parsers;

import net.unixcode.rts.parser.api.IIterableStreamsProvider;
import net.unixcode.rts.parser.api.IParserRunner;
import net.unixcode.rts.parser.api.IParserRunnerProvider;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.Parser;
import org.antlr.v4.runtime.tree.ParseTreeListener;
import org.springframework.context.ApplicationContext;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public abstract class BaseRunnerProvider<L extends Lexer, P extends Parser> implements IParserRunnerProvider {
  protected List<String> argv = null;
  protected IIterableStreamsProvider streamsProvider;
  protected BaseExecutor<L, P> parserExecutor;
  protected ParseTreeListener listener;
  protected Iterator<CharStream> iterator;
  protected ApplicationContext applicationContext;



  public BaseRunnerProvider(ApplicationContext applicationContext, IIterableStreamsProvider streamsProvider, BaseExecutor<L, P> parserExecutor, ParseTreeListener listener) {
    this.applicationContext = applicationContext;
    this.streamsProvider = streamsProvider;
    this.parserExecutor = parserExecutor;
    this.listener = listener;
  }

  @Override
  public IParserRunnerProvider prepare(String[] argv) {
    this.argv = Arrays.asList(argv);
    this.streamsProvider = this.streamsProvider.apply(this.argv);
    this.iterator = streamsProvider.iterator();

    return this;
  }

  @Override
  public abstract IParserRunner get();

  protected void run(ParseTreeListener listener) {
    System.out.println("Parser started.");

    if (argv == null) {
      System.err.println("ARGV is not set");
      System.err.println("Call .prepare() first.");

      System.exit(1);
    }

    CharStream stream = null;

    while(true) {
      synchronized (iterator) {
        if (iterator.hasNext()) {
          stream = iterator.next();
        }
      }

      if (stream == null) {
        break;
      }

      var parser = this.parserExecutor.apply(stream);
      this.parserExecutor.exec(parser, listener);

      stream = null;
    }

    System.out.println("Parser done.");
  }
}
