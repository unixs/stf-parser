package net.unixcode.rts.parser.api.compiler.antlr;

import net.unixcode.rts.parser.api.compiler.ISourceItem;
import org.antlr.v4.runtime.CharStream;


public interface IANTLRSourceItem extends ISourceItem {
  CharStream getStream();
}
