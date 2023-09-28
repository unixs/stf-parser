package net.unixcode.rts.parser.parsers;

import net.unixcode.rts.parser.api.*;
import net.unixcode.rts.parser.api.stf.ISTF2XMLListenerCtxt;
import net.unixcode.rts.parser.parsers.stf.STF2XMLListener;
import net.unixcode.rts.parser.translator.XML2CXXTranslator;
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
  protected XML2CXXTranslator translator;

  public BaseRunnerProvider(ApplicationContext applicationContext, IIterableStreamsProvider streamsProvider, BaseExecutor<L, P> parserExecutor, IParserEmitter emitter, XML2CXXTranslator translator) {
    this.applicationContext = applicationContext;
    this.streamsProvider = streamsProvider;
    this.parserExecutor = parserExecutor;
    this.emitter = emitter;
    this.translator = translator;
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

  /**
   * This function is body of the parsing Thread from pool
   */
  protected void run() {
    // TODO: refactor it
    if (argv == null) {
      System.err.println("ARGV is not set");
      System.err.println("Call .prepare() first.");

      System.exit(1);
    }

    process();
  }

  protected void process() {
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

      compile(sourceItem);

      sourceItem = null;
    }
  }

  protected void compile(ISourceItem sourceItem) {
    IParserListener listener = applicationContext.getBean(STF2XMLListener.class);

    var context = execute(sourceItem, listener);

    translator.accept((ISTF2XMLListenerCtxt) context);

    emitOutputData(listener);

  }

  protected void emitOutputData(@NotNull IParserListener listener) {
    this.emitter.accept(listener.getContext());
  }

  protected IParserListenerContext execute(ISourceItem sourceItem, @NotNull IParserListener listener) {
    if (listener.getContext().processed()) {
      throw new IllegalArgumentException("Listener context already processed for: [" + listener.getContext().getSourcePath() + "]");
    }

    var parser = this.parserExecutor.apply(sourceItem);

    // Copying source path from source item to listener context
    listener.getContext().setSourcePath(sourceItem.getSourcePath());

    // Marking the listener related context as processed and ready for data emitting
    listener.getContext().setProcessed();

    return this.parserExecutor.exec(parser, listener);
  }
}
