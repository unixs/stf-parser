package net.unixcode.rts.parser.api;

import net.unixcode.rts.parser.services.FilesInputStreamsProvider;
import org.antlr.v4.runtime.CharStream;
import org.jetbrains.annotations.NotNull;

import java.util.Iterator;
import java.util.List;
import java.util.function.Function;

public interface IInputStreamsProvider extends Iterable<CharStream>, Function<List<String>, IInputStreamsProvider> {
  @NotNull
  @Override
  Iterator<CharStream> iterator();
}
