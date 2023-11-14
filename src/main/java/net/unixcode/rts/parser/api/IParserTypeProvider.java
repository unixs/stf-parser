package net.unixcode.rts.parser.api;

import net.unixcode.rts.parser.api.compiler.xml.XMLType;

@FunctionalInterface
public interface IParserTypeProvider {
  XMLType getParserType(String[] argv);
}
