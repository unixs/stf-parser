package net.unixcode.rts.parser.compiler;

import net.unixcode.rts.parser.api.compiler.*;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope(BeanDefinition.SCOPE_PROTOTYPE)
public class DefaultCompiler implements ICompiler {

  protected ICompilerStrategy compilerStrategy;

  @Override
  public void emit(@NotNull ISourceItem sourceItem) {
    compilerStrategy.emit(sourceItem);
  }

  @Override
  public void setCompileStrategy(@NotNull ICompilerStrategy strategy) {
    this.compilerStrategy = strategy;
  }

  @Override
  public void accept(@NotNull ISourceItem sourceItem) {
    compilerStrategy.accept(sourceItem);
  }
}
