package net.unixcode.rts.parser.api.compiler.xml;

import net.unixcode.rts.parser.api.compiler.ISourceItem;
import net.unixcode.rts.parser.api.compiler.ITypedSourceItem;

public interface IXMLSourceItem extends ITypedSourceItem<XMLType> {
  @Override
  IXMLTransformerContext getContext();
  XMLType getType();
}
