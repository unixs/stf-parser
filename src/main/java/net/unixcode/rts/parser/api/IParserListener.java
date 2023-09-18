package net.unixcode.rts.parser.api;

import org.antlr.v4.runtime.tree.ParseTreeListener;

public interface IParserListener extends ParseTreeListener  {
  IParserListenerContext getContext();
}
