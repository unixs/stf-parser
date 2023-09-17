package net.unixcode.rts.parser.services;

import net.unixcode.rts.parser.api.IFileNamesProvider;
import net.unixcode.rts.parser.api.IIterableStreamsProvider;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

@Service
public class FilesIterableSyncStreamsProviderProvider implements IIterableStreamsProvider {
  IFileNamesProvider fileNamesProvider;
  List<String> argv;

  @Autowired
  public FilesIterableSyncStreamsProviderProvider(IFileNamesProvider fileNamesProvider) {
    this.fileNamesProvider = fileNamesProvider;
  }

  @Override
  public @NotNull Iterator<CharStream> iterator() {
    return new InternalSyncIterator();
  }

  @Override
  public IIterableStreamsProvider apply(List<String> argv) {
    this.argv = argv;

    return this;
  }

  protected class InternalSyncIterator implements Iterator<CharStream> {
    private final Iterator<String> filesIter;
    InternalSyncIterator() {
      List<String> fileNames = Collections.synchronizedList(fileNamesProvider.apply(argv));
      filesIter = fileNames.iterator();
    }

    @Override
    public boolean hasNext() {
      return filesIter.hasNext();
    }

    @Override
    public @NotNull CharStream next() {
      String filePath = filesIter.next();
      try {
        return CharStreams.fromFileName(filePath);
      } catch (IOException e) {
        throw new RuntimeException(e);
      }
    }
  }
}
