package net.unixcode.rts.parser.api.compiler.antlr.stf;

import net.unixcode.rts.parser.api.compiler.xml.IXMLSettings;
import net.unixcode.rts.parser.api.compiler.xml.XMLType;
import org.w3c.dom.Document;

import java.util.function.Function;

@FunctionalInterface
public interface ISTF2XMLSettingsProvider extends Function<XMLType, IXMLSettings> {
}
