package net.unixcode.rts.parser.services;

import net.unixcode.rts.parser.api.compiler.ISourceItem;
import org.apache.commons.io.FilenameUtils;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;

@Component
@Scope(BeanDefinition.SCOPE_PROTOTYPE)
public class FileEmitter extends STDOUTEmitter {
  protected String extension;

  @Override
  protected OutputStreamWriter getOutputStreamWriter(ISourceItem sourceItem) {
    try {
      var targetPath = getOutFilePath(sourceItem);
      var file = new File(targetPath);

      sourceItem.setOutPath(targetPath);
      log.info("compiled file: " + targetPath);

      return new FileWriter(file, StandardCharsets.UTF_8);
    }
    catch (IOException e) {
      log.error("Unable to write output file.");
      log.error(e.getMessage());

      throw new RuntimeException(e);
    }
  }

  public FileEmitter setExtension(@NotNull String extension) {
    this.extension = extension;
    return this;
  }

  protected String getOutFilePath(@NotNull ISourceItem sourceItem) {
    if (extension == null) {
      throw new IllegalArgumentException("File extension not defined for FileEmitter.");
    }

    var srcPath = sourceItem.getSourcePath();

    return FilenameUtils.removeExtension(srcPath) + FilenameUtils.EXTENSION_SEPARATOR + extension;
  }
}
