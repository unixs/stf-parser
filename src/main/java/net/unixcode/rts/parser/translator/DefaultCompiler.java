package net.unixcode.rts.parser.translator;

import net.unixcode.rts.parser.api.compiler.*;
import org.springframework.stereotype.Component;

@Component
public class DefaultCompiler implements ICompiler {

  protected ICompilerStrategy compilerStrategy;

  @Override
  public void emitOutput() {
    // noop
  }

  @Override
  public void setEmitterStrategy() {
    // noop
  }

  @Override
  public void setCompileStrategy(ICompilerStrategy strategy) {
    this.compilerStrategy = strategy;
  }

  @Override
  public void accept(ISourceItem sourceItem) {
    compilerStrategy.accept(sourceItem);
  }
}
