package net.unixcode.rts.parser.parsers;

import net.unixcode.rts.parser.api.ISourceItem;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;

import java.io.File;
import java.io.IOException;
import java.text.MessageFormat;

public class BaseSourceItem implements ISourceItem {
  protected String sourcePath;
  protected CharStream stream = null;

  public BaseSourceItem(String sourcePath) {
    if (sourcePath != null) {
      this.sourcePath = sourcePath;
    }

    try {
      stream = CharStreams.fromFileName(sourcePath);
    }
    catch (IOException e) {
      System.err.println(MessageFormat.format("ERROR: Unable to compile file [{0}]", sourcePath));
      System.err.println(e.getMessage());
    }
  }

  @Override
  public CharStream getStream() {
    return stream;
  }

  @Override
  public void setStream(CharStream stream) {
    this.stream = stream;
  }

  @Override
  public String getSourcePath() {
    return sourcePath;
  }
}