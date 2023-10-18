package net.unixcode.rts.parser.api;

import org.antlr.v4.runtime.Parser;

@FunctionalInterface
public interface IParserExecutor<P extends Parser> {
  IParserListenerContext exec(P parser, IParserListener listener);
}
