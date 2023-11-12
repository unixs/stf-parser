package net.unixcode.rts.parser.api.compiler.antlr.stf;

import net.unixcode.rts.parser.api.IParserListenerContext;
import org.w3c.dom.Document;

public interface ISTF2XMLListenerCtxt extends IParserListenerContext {
  String EXTENSION = "xml";
  @Override
  default String getFileExtensiion() {
    return EXTENSION;
  }

  Document getDoc();
}
