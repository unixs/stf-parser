package net.unixcode.rts.parser.compiler;

import net.unixcode.rts.parser.api.compiler.*;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class DefaultCompilerStrategy implements ICompilerStrategy {
  final protected static Logger log = LoggerFactory.getLogger(DefaultCompilerStrategy.class);

  protected ICompilerEmitter emitter;

  public DefaultCompilerStrategy(ICompilerEmitter emitter) {
    this.emitter = emitter;
  }

  @Override
  public abstract void accept(ISourceItem sourceItem);

  @Override
  public void emit(ISourceItem sourceItem) {
    this.emitter.accept(sourceItem);
  }

  protected static void checkSourceItem(@NotNull ISourceItem sourceItem, CompilerType compilerType) {
    if (sourceItem.getCompilerType() != compilerType) {
      throw new IllegalArgumentException("Wrong source item for compiler.");
    }
  }
}
