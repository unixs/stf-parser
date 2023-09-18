package net.unixcode.rts.parser.services;

import net.unixcode.rts.parser.api.IParserEmitter;
import net.unixcode.rts.parser.api.IParserListenerContext;
import org.springframework.stereotype.Service;

@Service
public class FileEmitter implements IParserEmitter {
  @Override
  public void accept(IParserListenerContext listenerContext) {
    System.out.println("Emit file for.");
    System.out.println("\t" + listenerContext.getSourcePath());
    System.out.println("\t as " + listenerContext.getExtensiion());
  }
}
