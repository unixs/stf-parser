package net.unixcode.rts.parser.api;

import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Lexer;

import java.util.function.Function;

@FunctionalInterface
public interface ILexerSupplier<T extends Lexer> extends Function<CharStream, T> {
  @Override
  T apply(CharStream cs);
}
