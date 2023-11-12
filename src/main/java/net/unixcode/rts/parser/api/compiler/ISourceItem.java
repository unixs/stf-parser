package net.unixcode.rts.parser.api.compiler;

import java.util.function.Supplier;

public interface ISourceItem extends Supplier<String> {
  CompilerType getCompilerType();
  default String getSourcePath() {
    return this.get();
  }
}
