package net.unixcode.rts.parser.api.stf;

import net.unixcode.rts.parser.api.IParserListenerContext;
import org.w3c.dom.Document;

import java.io.OutputStreamWriter;

public interface ISTF2XMLListenerCtxt extends IParserListenerContext {
  String EXTENSION = "xml";
  @Override
  default String getFileExtensiion() {
    return EXTENSION;
  }
}
