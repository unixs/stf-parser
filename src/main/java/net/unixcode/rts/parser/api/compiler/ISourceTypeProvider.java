package net.unixcode.rts.parser.api.compiler;

import java.util.function.Function;

@FunctionalInterface
public interface ISourceTypeProvider<T> extends Function<ISourceItem, T> {
}
