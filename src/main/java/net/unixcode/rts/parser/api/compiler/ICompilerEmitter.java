package net.unixcode.rts.parser.api.compiler;

import net.unixcode.rts.parser.api.compiler.ICompilerContext;

import java.util.function.Consumer;

@FunctionalInterface
public interface ICompilerEmitter extends Consumer<ISourceItem> {
}
