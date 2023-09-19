package net.unixcode.rts.parser.api;

import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Parser;

import java.util.function.Function;

@FunctionalInterface
public interface IParserConstructor<T extends Parser> extends Function<ISourceItem, T> {
}
