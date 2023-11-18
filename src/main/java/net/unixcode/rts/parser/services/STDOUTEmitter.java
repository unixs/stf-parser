package net.unixcode.rts.parser.services;

import net.unixcode.rts.parser.api.compiler.ICompilerEmitter;
import net.unixcode.rts.parser.api.compiler.ISourceItem;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.OutputStream;

@Component
public class STDOUTEmitter implements ICompilerEmitter {
  final protected Logger log = LoggerFactory.getLogger(getClass());

  @Override
  public void accept(@NotNull ISourceItem sourceItem) {
    var context = sourceItem.getContext();

    try {
      var streamWriter = getOutputStream(sourceItem);
      context.writeToStream(streamWriter);
      streamWriter.close();
    } catch (IOException e) {
      log.error("Unable to emit output.");
      log.error(e.getMessage());

      throw new RuntimeException(e);
    }
  }

  protected OutputStream getOutputStream(ISourceItem sourceItem) {
    return System.out;
  }
}
