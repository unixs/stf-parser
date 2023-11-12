package net.unixcode.rts.parser.translator;

import net.unixcode.rts.parser.api.compiler.CompilerType;
import net.unixcode.rts.parser.api.compiler.ICompilerStrategy;
import net.unixcode.rts.parser.api.compiler.ISourceItem;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class DefaultCompilerStrategy implements ICompilerStrategy {
  final protected static Logger log = LoggerFactory.getLogger(DefaultCompilerStrategy.class);

  @Override
  public abstract void accept(ISourceItem iSourceItem);

  protected static void checkSourceItem(ISourceItem sourceItem, CompilerType compilerType) {
    if (sourceItem.getCompilerType() != compilerType) {
      throw new IllegalArgumentException("Wrong source item for STF compiler.");
    }
  }
}
