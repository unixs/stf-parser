package net.unixcode.rts.parser.api.compiler;

import java.util.function.Supplier;

public interface ISourceItem extends Supplier<String> {
  CompilerType getCompilerType();
  ICompilerContext getContext();
  void setContext(ICompilerContext context);
  default String getSourcePath() {
    return this.get();
  }
}
