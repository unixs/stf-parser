package net.unixcode.rts.parser.api.compiler.xml;

import java.util.function.Function;

public interface IXMLTransformerProvider extends Function<XMLType, IXMLTransformer> {
}
