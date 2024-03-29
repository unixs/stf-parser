package net.unixcode.rts.parser.api;

import net.unixcode.rts.parser.api.compiler.antlr.IANTLRSourceItem;
import org.antlr.v4.runtime.Parser;

import java.util.function.Function;

@FunctionalInterface
public interface IParserConstructor<T extends Parser> extends Function<IANTLRSourceItem, T> {
}
