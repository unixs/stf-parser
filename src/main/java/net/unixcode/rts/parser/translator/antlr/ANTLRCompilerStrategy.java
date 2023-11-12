package net.unixcode.rts.parser.translator.antlr;

import net.unixcode.rts.parser.api.IParserEmitter;
import net.unixcode.rts.parser.api.IParserListenerContext;
import net.unixcode.rts.parser.api.compiler.ISourceItem;
import net.unixcode.rts.parser.api.compiler.antlr.IANTLRParserListener;
import net.unixcode.rts.parser.api.compiler.antlr.IANTLRSourceItem;
import net.unixcode.rts.parser.translator.DefaultCompilerStrategy;
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.Parser;
import org.jetbrains.annotations.NotNull;

public abstract class ANTLRCompilerStrategy<L extends Lexer, P extends Parser> extends DefaultCompilerStrategy {
  protected BaseANTLRExecutor<L, P> parserExecutor;
  protected IParserEmitter emitter;

  public ANTLRCompilerStrategy(BaseANTLRExecutor<L, P> parserExecutor, IParserEmitter emitter) {
    this.parserExecutor = parserExecutor;
    this.emitter = emitter;
  }

  @Override
  abstract public void accept(ISourceItem sourceItem);


  protected void emitOutputData(@NotNull IANTLRParserListener listener) {
    this.emitter.accept(listener.getContext());
  }

  protected IParserListenerContext execute(IANTLRSourceItem sourceItem, @NotNull IANTLRParserListener listener) {
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
