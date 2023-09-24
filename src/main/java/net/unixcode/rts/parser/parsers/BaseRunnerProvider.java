package net.unixcode.rts.parser.parsers;

import net.unixcode.rts.parser.api.*;
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.Parser;
import org.jetbrains.annotations.NotNull;
import org.springframework.context.ApplicationContext;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public abstract class BaseRunnerProvider<L extends Lexer, P extends Parser> implements IParserRunnerProvider {
  protected List<String> argv = null;
  protected IIterableStreamsProvider streamsProvider;
  protected BaseExecutor<L, P> parserExecutor;
  protected Iterator<ISourceItem> iterator;
  protected ApplicationContext applicationContext;
  protected IParserEmitter emitter;

  public BaseRunnerProvider(ApplicationContext applicationContext, IIterableStreamsProvider streamsProvider, BaseExecutor<L, P> parserExecutor, IParserEmitter emitter) {
    this.applicationContext = applicationContext;
    this.streamsProvider = streamsProvider;
    this.parserExecutor = parserExecutor;
    this.emitter = emitter;
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

  protected void run(IParserListener listener) {
    System.out.println("Parser started.");

    // TODO: refactor it
    if (argv == null) {
      System.err.println("ARGV is not set");
      System.err.println("Call .prepare() first.");

      System.exit(1);
    }

    parse(listener);

    emitOutputData(listener);

    System.out.println("Parser done.");
  }

  protected void parse(IParserListener listener) {
    ISourceItem sourceItem = null;

    while(true) {
      synchronized (iterator) {
        if (iterator.hasNext()) {
          sourceItem = iterator.next();
        }
      }

      if (sourceItem == null) {
        break;
      }

      if (sourceItem.getStream() == null) {
        System.err.println("Source stream is NULL. Trying next source item.");

        sourceItem = null;
        continue;
      }

      execute(sourceItem, listener);

      sourceItem = null;
    }
  }

  protected void emitOutputData(@NotNull IParserListener listener) {
    this.emitter.accept(listener.getContext());
  }

  protected void execute(ISourceItem sourceItem, IParserListener listener) {
    var parser = this.parserExecutor.apply(sourceItem);

    // Copying source path from source item to listener context
    listener.getContext().setSourcePath(sourceItem.getSourcePath());

    // Marking the listener related context as processed and ready for data emitting
    listener.getContext().setProcessed();

    this.parserExecutor.exec(parser, listener);
  }
}
