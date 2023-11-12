package net.unixcode.rts.parser.api;

import net.unixcode.rts.parser.api.compiler.ISourceItem;
import org.jetbrains.annotations.NotNull;

import java.util.Iterator;
import java.util.List;
import java.util.function.Function;

public interface IIterableSourcesProvider extends Iterable<ISourceItem>, Function<List<String>, IIterableSourcesProvider> {
  @NotNull
  @Override
  Iterator<ISourceItem> iterator();
}
