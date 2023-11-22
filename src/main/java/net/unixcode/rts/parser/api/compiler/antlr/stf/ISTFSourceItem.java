package net.unixcode.rts.parser.api.compiler.antlr.stf;

import net.unixcode.rts.parser.api.compiler.ITypedSourceItem;
import net.unixcode.rts.parser.api.compiler.antlr.IANTLRSourceItem;

public interface ISTFSourceItem extends IANTLRSourceItem, ITypedSourceItem<STFType> {
  STFType getType();
}
