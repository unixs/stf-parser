package net.unixcode.rts.parser.services;

import net.unixcode.rts.parser.api.IParserEmitter;
import net.unixcode.rts.parser.api.IParserListenerContext;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.OutputStreamWriter;

@Component
public class STDOUTEmitter implements IParserEmitter  {
  final protected Logger log = LoggerFactory.getLogger(getClass());

  @Override
  public void accept(@NotNull IParserListenerContext context) {
    var streamWriter = getOutputStreamWriter(context);

    context.writeToStream(streamWriter);
  }

  protected OutputStreamWriter getOutputStreamWriter(IParserListenerContext context) {
    return new OutputStreamWriter(System.out);
  }
}
