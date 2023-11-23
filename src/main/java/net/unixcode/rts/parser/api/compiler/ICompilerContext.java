package net.unixcode.rts.parser.api.compiler;

import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.function.Consumer;

public interface ICompilerContext extends Consumer<OutputStream> {
  default void writeToStream(OutputStream outSriter) {
    this.accept(outSriter);
  }
  void setSourceItem(ISourceItem sourceItem);
  ISourceItem getSourceItem();
}
