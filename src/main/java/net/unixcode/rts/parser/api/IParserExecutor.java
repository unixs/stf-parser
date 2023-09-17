package net.unixcode.rts.parser.api;

import org.antlr.v4.runtime.Parser;
import org.antlr.v4.runtime.tree.ParseTreeListener;

@FunctionalInterface
public interface IParserExecutor<P extends Parser> {
  void exec(P parser, ParseTreeListener listener);
}
