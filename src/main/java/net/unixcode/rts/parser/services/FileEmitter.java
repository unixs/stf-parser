package net.unixcode.rts.parser.services;

import net.unixcode.rts.parser.api.IParserEmitter;
import net.unixcode.rts.parser.api.IParserListenerContext;
import org.springframework.stereotype.Service;

@Service
public class FileEmitter implements IParserEmitter {
  @Override
  public void accept(IParserListenerContext context) {
    if (!context.processed()) {
      System.err.println("Unprocessed context has been passed to emitter");

      return;
    }

    System.out.println("Emit file for.");
    System.out.println("\t" + context.getSourcePath());
    System.out.println("\t as " + context.getExtensiion());
  }
}
