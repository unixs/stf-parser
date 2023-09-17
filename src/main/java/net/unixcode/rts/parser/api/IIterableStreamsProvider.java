package net.unixcode.rts.parser.api;

import org.antlr.v4.runtime.CharStream;
import org.jetbrains.annotations.NotNull;

import java.util.Iterator;
import java.util.List;
import java.util.function.Function;

public interface IIterableStreamsProvider extends Iterable<CharStream>, Function<List<String>, IIterableStreamsProvider> {
  @NotNull
  @Override
  Iterator<CharStream> iterator();
}
