package net.unixcode.rts.parser.api;

import org.jetbrains.annotations.NotNull;

import java.util.Iterator;
import java.util.List;
import java.util.function.Function;

public interface IIterableStreamsProvider extends Iterable<ISourceItem>, Function<List<String>, IIterableStreamsProvider> {
  @NotNull
  @Override
  Iterator<ISourceItem> iterator();
}
