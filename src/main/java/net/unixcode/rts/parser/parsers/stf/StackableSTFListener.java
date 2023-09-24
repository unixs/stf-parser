package net.unixcode.rts.parser.parsers.stf;

import net.unixcode.rts.parser.antlr.stf.stfBaseListener;
import net.unixcode.rts.parser.api.IParserListener;
import net.unixcode.rts.parser.api.IParserListenerContext;
import net.unixcode.rts.parser.parsers.BaseListenerStackFrame;
import org.jetbrains.annotations.NotNull;

import java.util.EmptyStackException;
import java.util.Stack;

abstract class StackableSTFListener<T, F extends BaseListenerStackFrame<T>> extends stfBaseListener implements IParserListener {
  protected final Stack<F> stack = new Stack<>();

  protected F pop() {
    var frame = stack.pop();

    frame.unlink();

    return frame;
  }

  protected F push(@NotNull F frame) {
    try {
      var prevFrame = stack.peek();

      frame.link(prevFrame);
    }
    catch (EmptyStackException e) {
      // noop
    }

    return stack.push(frame);
  }

  protected F peek() {
    return stack.peek();
  }

  @Override
  public abstract IParserListenerContext getContext();
}
