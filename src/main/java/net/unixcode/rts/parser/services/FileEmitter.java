package net.unixcode.rts.parser.services;

import net.unixcode.rts.parser.api.IParserEmitter;
import net.unixcode.rts.parser.api.IParserListenerContext;
import org.apache.commons.io.FilenameUtils;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;

@Service
public class FileEmitter extends STDOUTEmitter {
  protected OutputStreamWriter getOutputStreamWriter(IParserListenerContext context) {
    try {
      var targetPath = getTargetFilePath(context);
      var file = new File(targetPath);

      System.out.println("Emit file for.");
      System.out.println("\t" + targetPath);
      System.out.println("\t as " + context.getFileExtensiion());

      return new FileWriter(file);
    }
    catch (IOException e) {
      System.err.println("Unable write output file.");
      System.err.println(e.getMessage());

      throw new RuntimeException(e);
    }
  }

  protected String getTargetFilePath(@NotNull IParserListenerContext context) {
    var srcPath = context.getSourcePath();
    var targetExtension = context.getFileExtensiion();

    return FilenameUtils.removeExtension(srcPath) + FilenameUtils.EXTENSION_SEPARATOR + targetExtension;
  }
}
