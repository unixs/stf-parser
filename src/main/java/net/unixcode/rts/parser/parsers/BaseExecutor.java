package net.unixcode.rts.parser.parsers;

import net.unixcode.rts.parser.api.*;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.Parser;
import org.antlr.v4.runtime.tree.ParseTreeListener;

public abstract class BaseExecutor<L extends Lexer, P extends Parser> implements IParserConstructor<P>, IParserExecutor<P> {
  protected ILexerSupplier<L> lexerSupplier;
  protected IParserSupplier<P> parserSupplier;

  public BaseExecutor(ILexerSupplier<L> lexerSupplier, IParserSupplier<P> parserSupplier) {
    this.lexerSupplier = lexerSupplier;
    this.parserSupplier = parserSupplier;
  }

  @Override
  public P apply(CharStream charStream) {
    Lexer lexer = lexerSupplier.apply(charStream);

    var tokenStreeam = new CommonTokenStream(lexer);

    return parserSupplier.apply(tokenStreeam);
  }

  public abstract void exec(P parser, IParserListener listener);
}
