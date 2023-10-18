package net.unixcode.rts.parser.api;

import java.util.function.Supplier;

public interface IParserRunnerProvider extends Supplier<IParserRunner> {
  IParserRunnerProvider prepare(String[] argv);
}
