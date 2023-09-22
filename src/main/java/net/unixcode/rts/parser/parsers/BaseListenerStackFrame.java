package net.unixcode.rts.parser.parsers;

import net.unixcode.rts.parser.api.IListenerStackFrame;
import org.jetbrains.annotations.NotNull;

import java.util.Iterator;

public abstract class BaseListenerStackFrame<T> implements IListenerStackFrame<T>, Iterable<IListenerStackFrame<T>> {
  protected IListenerStackFrame<T> prevFrame;
  protected T data;

  public BaseListenerStackFrame(IListenerStackFrame<T> prevFrame) {
    link(prevFrame);
  }

  @Override
  public boolean isFirst() {
    return prevFrame == null;
  }

  @Override
  public T getData() {
    return data;
  }

  @Override
  public void setData(T data) {
    this.data = data;
  }

  @Override
  public IListenerStackFrame<T> unlink() {
    this.prevFrame = null;

    return this;
  }

  @Override
  public IListenerStackFrame<T> link(IListenerStackFrame<T> frame) {
    this.prevFrame = frame;

    return this;
  }

  @Override
  public IListenerStackFrame<T> prev() {
    return prevFrame;
  }

  @NotNull
  @Override
  public Iterator<IListenerStackFrame<T>> iterator() {
    return new InnerIterator(this);
  }

  @Override
  abstract public IListenerStackFrame<T> createNext();

  protected class InnerIterator implements Iterator<IListenerStackFrame<T>> {
    private IListenerStackFrame<T> current = null;
    private boolean isFirst = true;

    public InnerIterator(IListenerStackFrame<T> start) {
      this.current = start;
    }

    @Override
    public boolean hasNext() {
      return !current.isFirst();
    }

    @Override
    public IListenerStackFrame<T> next() {
      if (isFirst) {
        isFirst = false;
        return current;
      }

      current = current.prev();

      return current;
    }
  }
}
