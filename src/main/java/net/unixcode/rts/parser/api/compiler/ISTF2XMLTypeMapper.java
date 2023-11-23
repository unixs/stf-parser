package net.unixcode.rts.parser.api.compiler;

import net.unixcode.rts.parser.api.compiler.antlr.stf.STFType;
import net.unixcode.rts.parser.api.compiler.xml.XMLType;

import java.util.function.Function;

@FunctionalInterface
public interface ISTF2XMLTypeMapper extends Function<STFType, XMLType> {
}
