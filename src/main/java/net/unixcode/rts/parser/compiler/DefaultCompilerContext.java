package net.unixcode.rts.parser.compiler;

import net.unixcode.rts.parser.api.compiler.ICompilerContext;
import net.unixcode.rts.parser.api.compiler.ISourceItem;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.OutputStream;

public abstract class DefaultCompilerContext implements ICompilerContext {
  final protected Logger log = LoggerFactory.getLogger(getClass());

  protected ISourceItem sourceItem;

  @Override
  public void setSourceItem(ISourceItem sourceItem) {
    this.sourceItem = sourceItem;
  }

  @Override
  public ISourceItem getSourceItem() {
    return sourceItem;
  }

  @Override
  public abstract void accept(OutputStream outputStream);
}
