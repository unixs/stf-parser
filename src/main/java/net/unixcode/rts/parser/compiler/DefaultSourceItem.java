package net.unixcode.rts.parser.compiler;

import net.unixcode.rts.parser.api.compiler.CompilerType;
import net.unixcode.rts.parser.api.compiler.ICompilerContext;
import net.unixcode.rts.parser.api.compiler.ISourceItem;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.text.MessageFormat;

public class DefaultSourceItem implements ISourceItem {
  final protected Logger log = LoggerFactory.getLogger(getClass());
  protected String sourcePath;
  protected CompilerType compilerType;

  protected ICompilerContext context;

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

  @Contract("_ -> new")
  public static @NotNull ISourceItem defaultSourceItemFactory(String srcPath) {
    return new DefaultSourceItem(srcPath, CompilerType.XML);
  }
}
