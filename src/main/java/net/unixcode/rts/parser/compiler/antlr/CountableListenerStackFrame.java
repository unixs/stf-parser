package net.unixcode.rts.parser.compiler.antlr;

import net.unixcode.rts.parser.api.ICountableStackFrame;
import net.unixcode.rts.parser.api.IListenerStackFrame;
import org.jetbrains.annotations.NotNull;

public abstract class CountableListenerStackFrame<T> extends BaseListenerStackFrame<T> implements ICountableStackFrame {
  protected Integer counter;

  public CountableListenerStackFrame(IListenerStackFrame<T> prevFrame) {
    super(prevFrame);
  }

  @Override
  public ICountableStackFrame initCounter(Integer start) {
    counter = start;

    return this;
  }

  @Override
  public boolean isCounter() {
    return counter != null;
  }

  @Override
  @NotNull
  public ICountableStackFrame count(Integer addition) {
    CountableListenerStackFrame<T> result = this;

    for (var frame : this) {
      result = (CountableListenerStackFrame<T>) frame;

      if (result.isCounter()) {
        result.counter += addition;

        break;
      }

      if (result.isFirst()) {
        throw new RuntimeException("Missing counter on stack bottom.");
      }
    }

    return result;
  }

  @Override
  public Integer getCounter() {
    CountableListenerStackFrame<T> result = this;

    for (var frame : this) {
      result = (CountableListenerStackFrame<T>) frame;

      if (result.isCounter()) {
        break;
      }

      if (result.isFirst()) {
        throw new RuntimeException("Missing counter on stack bottom.");
      }
    }

    return result.counter;
  }
}
