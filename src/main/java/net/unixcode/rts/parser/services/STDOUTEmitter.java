package net.unixcode.rts.parser.services;

import net.unixcode.rts.parser.api.IParserEmitter;
import net.unixcode.rts.parser.api.IParserListenerContext;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.io.OutputStreamWriter;

@Service
@Primary
public class STDOUTEmitter implements IParserEmitter  {
  @Override
  public void accept(IParserListenerContext context) {
    if (!context.processed()) {
      System.err.println("Unprocessed context has been passed to emitter");

      return;
    }

    var streamWriter = getOutputStreamWriter(context);

    context.writeToStream(streamWriter);
  }

  protected OutputStreamWriter getOutputStreamWriter(IParserListenerContext context) {
    return new OutputStreamWriter(System.out);
  }
}
