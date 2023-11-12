package net.unixcode.rts.parser.api;

import net.unixcode.rts.parser.api.compiler.xml.ParserType;

@FunctionalInterface
public interface IParserTypeProvider {
  ParserType getParserType(String[] argv);
}
