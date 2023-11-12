package net.unixcode.rts.parser.api.compiler;

import java.util.function.Function;

@FunctionalInterface
public interface ICompilerTypeProvider extends Function<String, CompilerType> {
}
