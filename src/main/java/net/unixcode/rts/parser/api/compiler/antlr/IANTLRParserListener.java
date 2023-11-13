package net.unixcode.rts.parser.api.compiler.antlr;

import org.antlr.v4.runtime.tree.ParseTreeListener;

public interface IANTLRParserListener extends ParseTreeListener  {
  IANTLRListenerContext getContext();
}
