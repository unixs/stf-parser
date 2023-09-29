package net.unixcode.rts.parser.services;

import net.unixcode.rts.parser.api.IParserListenerContext;
import org.apache.commons.io.FilenameUtils;
import org.jetbrains.annotations.NotNull;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;

@Primary
@Component
public class FileEmitter extends STDOUTEmitter {
  protected OutputStreamWriter getOutputStreamWriter(IParserListenerContext context) {
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

  protected String getTargetFilePath(@NotNull IParserListenerContext context) {
    var srcPath = context.getSourcePath();
    var targetExtension = context.getFileExtensiion();

    return FilenameUtils.removeExtension(srcPath) + FilenameUtils.EXTENSION_SEPARATOR + targetExtension;
  }
}
