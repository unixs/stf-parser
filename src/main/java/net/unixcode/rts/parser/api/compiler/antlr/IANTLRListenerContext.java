package net.unixcode.rts.parser.api.compiler.antlr;

import net.unixcode.rts.parser.api.compiler.ICompilerContext;

public interface IANTLRListenerContext extends ICompilerContext {
  boolean processed();
  void setProcessed();
}
