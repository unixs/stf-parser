package net.unixcode.rts.parser.api.compiler;

import org.jetbrains.annotations.NotNull;

import java.util.function.Consumer;

public interface ICompiler extends Consumer<ISourceItem> {
  void emit(@NotNull ISourceItem sourceItem);
  void setCompileStrategy(ICompilerStrategy strategy);
}
