package net.unixcode.rts.parser.parsers;

import net.unixcode.rts.parser.api.*;
import org.antlr.v4.runtime.ANTLRErrorListener;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.Parser;

public abstract class BaseExecutor<L extends Lexer, P extends Parser> implements IParserConstructor<P>, IParserExecutor<P> {
  protected ILexerSupplier<L> lexerSupplier;
  protected IParserSupplier<P> parserSupplier;
  protected ANTLRErrorListener errorListener;

  public BaseExecutor(ILexerSupplier<L> lexerSupplier, IParserSupplier<P> parserSupplier, ANTLRErrorListener errorListener) {
    this.lexerSupplier = lexerSupplier;
    this.parserSupplier = parserSupplier;
    this.errorListener = errorListener;
  }

  @Override
  public P apply(ISourceItem sourceItem) {
    var stream = sourceItem.getStream();

    Lexer lexer = lexerSupplier.apply(stream);

    lexer.removeErrorListeners();
    lexer.addErrorListener(errorListener);

    var tokenStreeam = new CommonTokenStream(lexer);

    return parserSupplier.apply(tokenStreeam);
  }

  public abstract IParserListenerContext exec(P parser, IParserListener listener);
}
