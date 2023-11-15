package net.unixcode.rts.parser.compiler;

import net.unixcode.rts.parser.api.*;
import net.unixcode.rts.parser.api.compiler.ICompiler;
import net.unixcode.rts.parser.api.compiler.ICompilerStrategyProvider;
import net.unixcode.rts.parser.api.compiler.ISourceItem;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

@Component
public class DefaultCompilerRunner implements ICompilerRunner {
  final protected Logger log = LoggerFactory.getLogger(getClass());
  protected List<String> argv;
  protected IIterableSourcesProvider sourcesProvider;
  protected Iterator<ISourceItem> iterator;
  protected ICompilerStrategyProvider compilerStrategyFactory;
  protected ICompilerFactory compilerFactory;

  public DefaultCompilerRunner(ICompilerFactory compilerFactory, ICompilerStrategyProvider compilerStrategyFactory, @NotNull IIterableSourcesProvider sourcesProvider) {
    this.compilerFactory = compilerFactory;
    this.compilerStrategyFactory = compilerStrategyFactory;
    this.sourcesProvider = sourcesProvider;
  }

  /**
   * This function is body of the parsing Thread from pool
   */
  public void run() {
    if (argv == null) {
      throw new IllegalArgumentException("Sources not set.");
    }

    var compiler = compilerFactory.get();

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

      compile(compiler, sourceItem);

      sourceItem = null;
    }
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

  protected void compile(@NotNull ICompiler compiler, @NotNull ISourceItem sourceItem) {
    var compileStrategy = compilerStrategyFactory.apply(sourceItem.getCompilerType());

    compiler.setCompileStrategy(compileStrategy);

    compiler.accept(sourceItem);

    compiler.emit(sourceItem);
  }
}
