package net.unixcode.rts.parser.parsers.stf;

import net.unixcode.rts.parser.api.stf.ISTF2XMLListenerCtxt;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("prototype")
public class STF2XMLListenerCtxt implements ISTF2XMLListenerCtxt {
  public STF2XMLListenerCtxt() {
    System.out.println("New listener ctxt.");
  }

  @Override
  public String get() {
    return "String from STF2XML listener context.";
  }
}
