package net.unixcode.rts.parser.api.compiler.antlr.stf;

import net.unixcode.rts.parser.api.compiler.antlr.IANTLRListenerContext;
import org.w3c.dom.Document;

public interface ISTF2XMLListenerCtxt extends IANTLRListenerContext {
  Document getDoc();
}
