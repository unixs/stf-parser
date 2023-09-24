package net.unixcode.rts.parser.api;

public interface ICountableStackFrame {
  ICountableStackFrame initCounter(Integer start);
  boolean isCounter();
  ICountableStackFrame count(Integer addition);
  Integer getCounter();
  default ICountableStackFrame initCounter() {
    return initCounter(0);
  }
  default ICountableStackFrame count() {
    return count(1);
  }
}
