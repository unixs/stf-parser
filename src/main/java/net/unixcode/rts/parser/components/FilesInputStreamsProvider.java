package net.unixcode.rts.parser.components;

import net.unixcode.rts.parser.api.IFileNamesProvider;
import net.unixcode.rts.parser.api.IInputStreamsProvider;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;

@Service
public class FilesInputStreamsProvider implements IInputStreamsProvider {
  IFileNamesProvider fileNamesProvider;
  List<String> argv;

  @Autowired
  public FilesInputStreamsProvider(IFileNamesProvider fileNamesProvider) {
    this.fileNamesProvider = fileNamesProvider;
  }

  @Override
  public @NotNull Iterator<CharStream> iterator() {
    return new InternalIterator();
  }

  public IInputStreamsProvider setArgv(List<String> argv) {
    this.argv = argv;

    return this;
  }

  private class InternalIterator implements Iterator<CharStream> {
    private final Iterator<String> filesIter;
    InternalIterator() {
      List<String> fileNames = fileNamesProvider.apply(argv);
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
