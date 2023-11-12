package net.unixcode.rts.parser.compiler;

import net.unixcode.rts.parser.api.compiler.*;
import org.apache.commons.io.FilenameUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.text.MessageFormat;

@Component
public class SourceItemProvider implements ISourceItemProvider {
  final protected Logger log = LoggerFactory.getLogger(getClass());


  protected ICompilerTypeProvider compilerTypeProvider;
  protected ISourceItemFactoryProvider sourceItemFactoryProvider;

  SourceItemProvider(ICompilerTypeProvider compilerTypeProvider, ISourceItemFactoryProvider sourceItemFactoryProvider) {
    this.compilerTypeProvider = compilerTypeProvider;
    this.sourceItemFactoryProvider = sourceItemFactoryProvider;
  }

  @Override
  public ISourceItem apply(String sourcePath) {
    try {
      var path = checkSrcPath(sourcePath);

      var ext = FilenameUtils.getExtension(path);

      var compilerType = compilerTypeProvider.apply(ext);

      var sourceItemFactory = sourceItemFactoryProvider.apply(compilerType);

      return sourceItemFactory.apply(path);
    }
    catch (IllegalArgumentException e) {
      log.error(e.getMessage());

      return null;
    }
  }

  protected String checkSrcPath(String sourcePath) {
    try {
      if (sourcePath != null) {

        var file = new File(sourcePath);
        var path = file.getCanonicalPath();

        if (!file.exists()) {
          throw new IOException("File does not exists.");
        }

        if (!file.isFile()) {
          throw new IOException("Specified path is not the file.");
        }

        if (!file.canRead()) {
          throw new IOException("Specified file does not readable.");
        }

        return path;
      }

      throw new IOException("Source path is NULL");
    }
    catch (IOException e) {
      log.error(MessageFormat.format("ERROR: Unable to compile file [{0}]", sourcePath));
      log.error(e.getMessage());

      throw new RuntimeException(e);
    }
  }
}
