package net.unixcode.rts.parser.api;

import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.Parser;

import java.util.function.Function;

@FunctionalInterface
public interface IParserSupplier<T extends Parser> extends Function<CommonTokenStream, T> {
  @Override
  T apply(CommonTokenStream ts);
}
