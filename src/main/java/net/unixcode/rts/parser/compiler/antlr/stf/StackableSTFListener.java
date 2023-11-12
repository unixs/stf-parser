package net.unixcode.rts.parser.compiler.antlr.stf;

import net.unixcode.rts.parser.antlr.stf.stfBaseListener;
import net.unixcode.rts.parser.api.compiler.antlr.IANTLRParserListener;
import net.unixcode.rts.parser.api.IParserListenerContext;
import net.unixcode.rts.parser.compiler.antlr.BaseListenerStackFrame;
import org.jetbrains.annotations.NotNull;

import java.util.EmptyStackException;
import java.util.Stack;

abstract class StackableSTFListener<T, F extends BaseListenerStackFrame<T>> extends stfBaseListener implements IANTLRParserListener {
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

  protected T peekData() {
    return stack.peek().getData();
  }

  @Override
  public abstract IParserListenerContext getContext();
}
