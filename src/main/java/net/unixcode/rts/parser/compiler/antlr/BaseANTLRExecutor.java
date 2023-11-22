package net.unixcode.rts.parser.compiler.antlr;

import net.unixcode.rts.parser.api.*;
import net.unixcode.rts.parser.api.compiler.antlr.IANTLRListenerContext;
import net.unixcode.rts.parser.api.compiler.antlr.IANTLRParserListener;
import net.unixcode.rts.parser.api.compiler.antlr.IANTLRSourceItem;
import org.antlr.v4.runtime.ANTLRErrorListener;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.Parser;
import org.jetbrains.annotations.NotNull;

public abstract class BaseANTLRExecutor<L extends Lexer, P extends Parser> implements IParserConstructor<P>, IParserExecutor<P> {
  protected ILexerSupplier<L> lexerSupplier;
  protected IParserSupplier<P> parserSupplier;
  protected ANTLRErrorListener errorListener;

  public BaseANTLRExecutor(ILexerSupplier<L> lexerSupplier, IParserSupplier<P> parserSupplier, ANTLRErrorListener errorListener) {
    this.lexerSupplier = lexerSupplier;
    this.parserSupplier = parserSupplier;
    this.errorListener = errorListener;
  }

  @Override
  public P apply(@NotNull IANTLRSourceItem sourceItem) {
    var stream = sourceItem.getStream();

    Lexer lexer = lexerSupplier.apply(stream);

    lexer.removeErrorListeners();
    lexer.addErrorListener(errorListener);

    var tokenStreeam = new CommonTokenStream(lexer);

    return parserSupplier.apply(tokenStreeam);
  }

  public abstract IANTLRListenerContext exec(P parser, IANTLRParserListener listener);
}
