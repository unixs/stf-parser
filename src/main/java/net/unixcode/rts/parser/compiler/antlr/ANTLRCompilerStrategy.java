package net.unixcode.rts.parser.compiler.antlr;

import net.unixcode.rts.parser.api.compiler.ICompilerEmitter;
import net.unixcode.rts.parser.api.compiler.antlr.IANTLRListenerContext;
import net.unixcode.rts.parser.api.compiler.ISourceItem;
import net.unixcode.rts.parser.api.compiler.antlr.IANTLRParserListener;
import net.unixcode.rts.parser.api.compiler.antlr.IANTLRSourceItem;
import net.unixcode.rts.parser.compiler.DefaultCompilerStrategy;
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.Parser;
import org.jetbrains.annotations.NotNull;

public abstract class ANTLRCompilerStrategy<L extends Lexer, P extends Parser> extends DefaultCompilerStrategy {
  protected BaseANTLRExecutor<L, P> parserExecutor;


  public ANTLRCompilerStrategy(BaseANTLRExecutor<L, P> parserExecutor, ICompilerEmitter emitter) {
    super(emitter);
    this.parserExecutor = parserExecutor;
  }

  @Override
  public abstract void accept(ISourceItem sourceItem);

  protected IANTLRListenerContext execute(IANTLRSourceItem sourceItem, @NotNull IANTLRParserListener listener) {
    if (listener.getContext().processed()) {
      throw new IllegalArgumentException("Listener context already processed for: [" + sourceItem.getSourcePath() + "]");
    }

    var parser = this.parserExecutor.apply(sourceItem);

    // Marking the listener related context as processed and ready for data emitting
    listener.getContext().setProcessed();

    return this.parserExecutor.exec(parser, listener);
  }
}
