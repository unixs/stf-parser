package net.unixcode.rts.parser.parsers.stf;

import net.unixcode.rts.parser.api.IParserListener;
import net.unixcode.rts.parser.api.IParserListenerContext;
import net.unixcode.rts.parser.api.stf.ISTF2XMLListenerCtxt;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("prototype")
public class STF2XMLListenerCtxt implements ISTF2XMLListenerCtxt {
  final public static String EXTENSION = "xml";
  protected String sourcePath;
  protected IParserListener listener;

  public STF2XMLListenerCtxt() {
    System.out.println("New listener ctxt.");
  }

  @Override
  public IParserListenerContext setSourcePath(String path) {
    sourcePath = path;

    return this;
  }

  @Override
  public String getSourcePath() {
    return sourcePath;
  }

  @Override
  public String getExtensiion() {
    return EXTENSION;
  }
}
