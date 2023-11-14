package net.unixcode.rts.parser.compiler;

import net.unixcode.rts.parser.api.compiler.CompilerType;
import net.unixcode.rts.parser.api.compiler.ICompilerContext;
import net.unixcode.rts.parser.api.compiler.ISourceItem;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.text.MessageFormat;

public class DefaultSourceItem implements ISourceItem {
  final protected Logger log = LoggerFactory.getLogger(getClass());
  protected String sourcePath;
  protected CompilerType compilerType;
  protected ICompilerContext context;
  protected String outPath;

  protected InputStream inputStream;
  public DefaultSourceItem(String sourcePath, CompilerType compilerType) {
    this.compilerType = compilerType;

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
    }
    catch (IOException e) {
      log.error(MessageFormat.format("ERROR: Unable to compile file [{0}]", sourcePath));
      log.error(e.getMessage());
    }
  }

  @Override
  public String get() {
    return sourcePath;
  }

  @Override
  public CompilerType getCompilerType() {
    return compilerType;
  }

  @Override
  public ICompilerContext getContext() {
    return this.context;
  }

  @Override
  public void setContext(@NotNull ICompilerContext context) {
    this.context = context;
  }

  public ISourceItem setOutPath(String outPath) {
    this.outPath = outPath;

    return this;
  }

  @Override
  public InputStream getInputStream() {
    if (inputStream == null) {
      try {
        inputStream = new FileInputStream(getSourcePath());
      } catch (FileNotFoundException e) {
        log.error("File not found: " + sourcePath);
        log.error(e.getMessage());

        throw new RuntimeException(e);
      }
    }

    return inputStream;
  }

  @Override
  public String getOutPath() {
    return outPath;
  }
}
