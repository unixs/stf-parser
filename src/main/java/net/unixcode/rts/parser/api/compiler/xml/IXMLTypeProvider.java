package net.unixcode.rts.parser.api.compiler.xml;

import net.unixcode.rts.parser.api.compiler.ISourceItem;

import java.util.function.Function;

public interface IXMLTypeProvider extends Function<ISourceItem, XMLType> {
}
