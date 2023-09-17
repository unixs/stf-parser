package net.unixcode.rts.parser.api;

import org.springframework.stereotype.Component;

@FunctionalInterface
@Component
public interface IParserTypeProvider {
  ParserType getParserType(String[] argv);
}
