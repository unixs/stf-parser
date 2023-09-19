package net.unixcode.rts.parser.services;

import net.unixcode.rts.parser.api.IParserEmitter;
import net.unixcode.rts.parser.api.IParserListenerContext;
import org.apache.commons.io.FilenameUtils;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;

import java.io.File;

@Service
public class FileEmitter implements IParserEmitter {
  @Override
  public void accept(IParserListenerContext context) {
    if (!context.processed()) {
      System.err.println("Unprocessed context has been passed to emitter");

      return;
    }

    var targetPath = getTargetFilePath(context);

    System.out.println("Emit file for.");
    System.out.println("\t" + targetPath);
    System.out.println("\t as " + context.getExtensiion());
  }

  protected String getTargetFilePath(@NotNull IParserListenerContext context) {
    var srcPath = context.getSourcePath();
    var targetExtension = context.getExtensiion();

    return FilenameUtils.removeExtension(srcPath) + FilenameUtils.EXTENSION_SEPARATOR + targetExtension;
  }
}
