package net.unixcode.rts.parser.services;

import net.unixcode.rts.parser.api.compiler.ICompilerContext;
import org.apache.commons.io.FilenameUtils;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;

@Component
public class FileEmitter extends STDOUTEmitter {
  protected String extension;

  @Override
  protected OutputStreamWriter getOutputStreamWriter(ICompilerContext context) {
    try {
      var targetPath = getTargetFilePath(context);
      var file = new File(targetPath);

      log.info("compiled file: " + targetPath);

      return new FileWriter(file);
    }
    catch (IOException e) {
      log.error("Unable write output file.");
      log.error(e.getMessage());

      throw new RuntimeException(e);
    }
  }

  public FileEmitter setExtension(@NotNull String extension) {
    this.extension = extension;
    return this;
  }

  protected String getTargetFilePath(@NotNull ICompilerContext context) {
    if (extension == null) {
      throw new IllegalArgumentException("File extension not defined for FileEmitter.");
    }

    var srcPath = context.getSourcePath();

    return srcPath + FilenameUtils.EXTENSION_SEPARATOR + extension;
  }
}
