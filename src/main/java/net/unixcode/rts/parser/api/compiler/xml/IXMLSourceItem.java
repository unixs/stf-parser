package net.unixcode.rts.parser.api.compiler.xml;

import net.unixcode.rts.parser.api.compiler.ISourceItem;

public interface IXMLSourceItem extends ISourceItem {
  @Override
  IXMLTransformerContext getContext();
}
