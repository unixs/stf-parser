package net.unixcode.rts.parser.services;

import net.unixcode.rts.parser.api.IFileNamesProvider;
import net.unixcode.rts.parser.api.IIterableSourcesProvider;
import net.unixcode.rts.parser.api.compiler.ISourceItem;
import net.unixcode.rts.parser.api.compiler.ISourceItemProvider;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.Iterator;
import java.util.List;

@Component
public class FilesIterableSyncStreamsProvider implements IIterableSourcesProvider {
  protected IFileNamesProvider fileNamesProvider;
  protected ISourceItemProvider sourceItemProvider;
  protected List<String> argv;

  @Autowired
  public FilesIterableSyncStreamsProvider(IFileNamesProvider fileNamesProvider, ISourceItemProvider sourceItemProvider) {
    this.fileNamesProvider = fileNamesProvider;
    this.sourceItemProvider = sourceItemProvider;
  }

  @Override
  public @NotNull Iterator<ISourceItem> iterator() {
    return new InternalSyncIterator();
  }

  @Override
  public IIterableSourcesProvider apply(List<String> argv) {
    this.argv = argv;

    return this;
  }

  protected class InternalSyncIterator implements Iterator<ISourceItem> {
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
    @NotNull
    public ISourceItem next() {
      String filePath = filesIter.next();

      return sourceItemProvider.apply(filePath);
    }
  }
}
