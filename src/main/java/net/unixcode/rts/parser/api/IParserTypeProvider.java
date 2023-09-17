package net.unixcode.rts.parser.api;

import org.springframework.stereotype.Component;

@FunctionalInterface
public interface IParserTypeProvider {
  ParserType getParserType(String[] argv);
}
