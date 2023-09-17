package net.unixcode.rts.parser.api;

@FunctionalInterface
public interface IParserTypeProvider {
  ParserType getParserType(String[] argv);
}
