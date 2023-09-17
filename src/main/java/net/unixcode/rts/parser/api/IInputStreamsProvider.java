package net.unixcode.rts.parser.api;

import org.antlr.v4.runtime.CharStream;
import org.jetbrains.annotations.NotNull;

import java.util.Iterator;
import java.util.List;

public interface IInputStreamsProvider extends Iterable<CharStream> {
  @NotNull
  @Override
  Iterator<CharStream> iterator();

  IInputStreamsProvider setArgv(List<String> argv);
}
