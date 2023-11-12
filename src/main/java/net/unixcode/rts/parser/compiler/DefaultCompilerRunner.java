package net.unixcode.rts.parser.compiler;

import net.unixcode.rts.parser.api.*;
import net.unixcode.rts.parser.api.compiler.ICompiler;
import net.unixcode.rts.parser.api.compiler.ICompilerStrategyFactoryProvider;
import net.unixcode.rts.parser.api.compiler.ISourceItem;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

@Component
@Scope(BeanDefinition.SCOPE_PROTOTYPE)
public class DefaultCompilerRunner implements ICompilerRunner {
  final protected Logger log = LoggerFactory.getLogger(getClass());
  protected List<String> argv;
  protected IIterableSourcesProvider sourcesProvider;
  protected Iterator<ISourceItem> iterator;
  protected ICompilerStrategyFactoryProvider compilerStrategyFactoryProvider;
  protected ICompiler compiler;

  public DefaultCompilerRunner(ICompiler compiler, ICompilerStrategyFactoryProvider compilerStrategyFactoryProvider, @NotNull IIterableSourcesProvider sourcesProvider) {
    this.compiler = compiler;
    this.compilerStrategyFactoryProvider = compilerStrategyFactoryProvider;
    this.sourcesProvider = sourcesProvider;
  }

  /**
   * This function is body of the parsing Thread from pool
   */
  public void run() {
    if (argv == null) {
      throw new IllegalArgumentException("Sources not set.");
    }

    ISourceItem sourceItem = null;

    while(true) {
      synchronized (iterator) {
        if (iterator.hasNext()) {
          sourceItem = iterator.next();
        }
      }

      if (sourceItem == null) {
        break;
      }

      // TODO: something wrong here
      if (sourceItem.getSourcePath() == null) {
        log.warn("Source paht is NULL. Trying next source item.");

        sourceItem = null;
        continue;
      }

      compile(sourceItem);

      sourceItem = null;
    }
  }

  protected void compile(@NotNull ISourceItem sourceItem) {
    var compileStrategy = compilerStrategyFactoryProvider.apply(sourceItem.getCompilerType());

    compiler.setCompileStrategy(compileStrategy);

    compiler.accept(sourceItem);

    compiler.emit(sourceItem);
  }

  @Override
  public ICompilerRunner setSources(String @NotNull [] argv) {
    if (argv.length  == 0) {
      log.warn("Sources list is empty.");
    }

    this.argv = Arrays.asList(argv);
    this.sourcesProvider = this.sourcesProvider.apply(this.argv);
    this.iterator = sourcesProvider.iterator();

    return this;
  }
}
