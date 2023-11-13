package net.unixcode.rts.parser.api.compiler;

import java.util.function.Consumer;

public interface ICompilerStrategy extends Consumer<ISourceItem> {
  void emit(ISourceItem sourceItem);
}
