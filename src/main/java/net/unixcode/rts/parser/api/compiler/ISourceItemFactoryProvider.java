package net.unixcode.rts.parser.api.compiler;

import java.util.function.Function;

@FunctionalInterface
public interface ISourceItemFactoryProvider extends Function<CompilerType, ISourceItemFactory> {
}
