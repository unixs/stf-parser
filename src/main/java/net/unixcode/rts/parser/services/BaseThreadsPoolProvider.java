package net.unixcode.rts.parser.services;

import net.unixcode.rts.parser.api.IThreadsPoolProvider;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;

import java.util.Iterator;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Service
public class BaseThreadsPoolProvider implements IThreadsPoolProvider {
  final protected int CPU_MIN = 2;

  protected int cpus;
  protected int counter;
  protected ExecutorService execService;


  public BaseThreadsPoolProvider() {
    cpus = Runtime.getRuntime().availableProcessors();
    cpus = 1;

    if (cpus > CPU_MIN) {
      // Leave one cpu for user =)
      cpus--;
    }

    counter = cpus;

    execService = Executors.newFixedThreadPool(cpus);
  }

  @Override
  public @NotNull Iterator<Executor> iterator() {
    return new PoolIterator();
  }

  protected class PoolIterator implements Iterator<Executor> {
    @Override
    public boolean hasNext() {
      boolean hasNext = counter > 0;

      if (!hasNext) {
        execService.shutdown();
      }

      return hasNext;
    }

    @Override
    public Executor next() {
      counter--;

      return runner -> execService.execute(runner);
    }
  }
}
