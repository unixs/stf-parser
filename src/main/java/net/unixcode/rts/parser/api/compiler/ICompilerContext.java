package net.unixcode.rts.parser.api.compiler;

import java.io.OutputStreamWriter;
import java.util.function.Consumer;

public interface ICompilerContext extends Consumer<OutputStreamWriter> {
  default void writeToStream(OutputStreamWriter writer) {
    this.accept(writer);
  }
}
