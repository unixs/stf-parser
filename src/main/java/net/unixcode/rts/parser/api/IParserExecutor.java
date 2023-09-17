package net.unixcode.rts.parser.api;

import java.util.function.Function;
import org.antlr.v4.runtime.tree.ParseTreeListener;

@FunctionalInterface
public interface IParserExecutor {
  String exec(ParseTreeListener listener);
}
