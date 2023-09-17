package net.unixcode.rts.parser.api;

import java.util.function.Supplier;

public interface IParserStringEmitter extends Supplier<String> {
  String get();
}
