package net.unixcode.rts.parser.api;

import net.unixcode.rts.parser.api.compiler.antlr.IANTLRListenerContext;
import net.unixcode.rts.parser.api.compiler.antlr.IANTLRParserListener;
import org.antlr.v4.runtime.Parser;

@FunctionalInterface
public interface IParserExecutor<P extends Parser> {
  IANTLRListenerContext exec(P parser, IANTLRParserListener listener);
}
