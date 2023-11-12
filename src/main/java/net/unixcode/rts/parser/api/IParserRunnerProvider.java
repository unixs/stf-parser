package net.unixcode.rts.parser.api;

import java.util.function.Supplier;

@FunctionalInterface
public interface IParserRunnerProvider extends Supplier<ICompilerRunner> {
}
