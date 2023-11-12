package net.unixcode.rts.parser.api.compiler;

import java.util.function.Consumer;

public interface ICompiler extends Consumer<ISourceItem> {
  void emitOutput();
  void setEmitterStrategy();
  void setCompileStrategy(ICompilerStrategy strategy);
}
