package net.unixcode.rts.parser.api;

public interface IListenerStackFrame<T> {
  boolean isFirst();
  IListenerStackFrame<T> createNext();
  IListenerStackFrame<T> unlink();
  IListenerStackFrame<T> link(IListenerStackFrame<T> frame);
  T getData();
  IListenerStackFrame<T> setData(T data);
  IListenerStackFrame<T> prev();
}
