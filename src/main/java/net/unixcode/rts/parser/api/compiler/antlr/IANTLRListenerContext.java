package net.unixcode.rts.parser.api.compiler.antlr;

import net.unixcode.rts.parser.api.compiler.ICompilerContext;
import net.unixcode.rts.parser.api.compiler.ISourceItem;

public interface IANTLRListenerContext extends ICompilerContext {
  boolean processed();
  void setProcessed();
}
