package net.unixcode.rts.parser.api;

import net.unixcode.rts.parser.parsers.stf.STF2XMLListener;

public interface IListenerStackFrame<T> {
  boolean isFirst();
  IListenerStackFrame<T> createNext();
  IListenerStackFrame<T> unlink();
  IListenerStackFrame<T> link(IListenerStackFrame<T> frame);
  T getData();
  void setData(T data);
  IListenerStackFrame<T> prev();
}
