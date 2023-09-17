package net.unixcode.rts.parser.api;

import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.Parser;
import org.antlr.v4.runtime.tree.ParseTreeListener;

public abstract class BaseParserExecutor<L extends Lexer, P extends Parser> implements IParserConstructor<P>, IParserExecutor {
  protected ILexerSupplier<L> lexerSupplier;
  protected IParserSupplier<P> parserSupplier;

  protected P parser;

  public BaseParserExecutor(ILexerSupplier<L> lexerSupplier, IParserSupplier<P> parserSupplier) {
    this.lexerSupplier = lexerSupplier;
    this.parserSupplier = parserSupplier;
  }

  @Override
  public IParserConstructor<P> apply(CharStream charStream) {
    Lexer lexer = lexerSupplier.apply(charStream);

    var tokenStreeam = new CommonTokenStream(lexer);

    parser = parserSupplier.apply(tokenStreeam);

    return this;
  }

  public abstract String exec(ParseTreeListener listener);
}
