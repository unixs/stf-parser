package net.unixcode.rts.parser.services;

import net.unixcode.rts.parser.api.compiler.ICompilerContext;
import net.unixcode.rts.parser.api.compiler.ICompilerEmitter;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.OutputStreamWriter;

@Component
public class STDOUTEmitter implements ICompilerEmitter {
  final protected Logger log = LoggerFactory.getLogger(getClass());

  @Override
  public void accept(@NotNull ICompilerContext context) {
    var streamWriter = getOutputStreamWriter(context);

    context.writeToStream(streamWriter);
  }

  protected OutputStreamWriter getOutputStreamWriter(ICompilerContext context) {
    return new OutputStreamWriter(System.out);
  }
}
