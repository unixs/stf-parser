package net.unixcode.rts.parser.api.compiler.antlr;

import net.unixcode.rts.parser.api.IParserListenerContext;
import org.antlr.v4.runtime.tree.ParseTreeListener;

public interface IANTLRParserListener extends ParseTreeListener  {
  IParserListenerContext getContext();
}
