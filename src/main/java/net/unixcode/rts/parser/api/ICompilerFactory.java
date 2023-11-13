package net.unixcode.rts.parser.api;

import net.unixcode.rts.parser.api.compiler.ICompiler;

import java.util.function.Supplier;

@FunctionalInterface
public interface ICompilerFactory extends Supplier<ICompiler> {
}
