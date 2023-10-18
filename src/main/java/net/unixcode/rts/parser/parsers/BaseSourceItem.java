package net.unixcode.rts.parser.parsers;

import net.unixcode.rts.parser.api.ISourceItem;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.text.MessageFormat;

public class BaseSourceItem implements ISourceItem {
  final protected Logger log = LoggerFactory.getLogger(getClass());
  protected String sourcePath;
  protected CharStream stream = null;

  public BaseSourceItem(String sourcePath) {
    try {
      if (sourcePath != null) {

        var file = new File(sourcePath);
        this.sourcePath = file.getCanonicalPath();

        if (!file.exists()) {
          throw new IOException("File does not exists.");
        }

        if (!file.isFile()) {
          throw new IOException("Specified path is not the file.");
        }

        if (!file.canRead()) {
          throw new IOException("Specified file does not readable.");
        }
      }

      stream = CharStreams.fromFileName(this.sourcePath, StandardCharsets.UTF_16);
    }
    catch (IOException e) {
      log.error(MessageFormat.format("ERROR: Unable to compile file [{0}]", sourcePath));
      log.error(e.getMessage());
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
