package net.unixcode.rts.parser.api;

import net.unixcode.rts.parser.api.compiler.ICompilerContext;

public interface IParserListenerContext extends ICompilerContext {
  IParserListenerContext setSourcePath(String path);
  @Override
  String getSourcePath();
  String getFileExtensiion();
  boolean processed();
  void setProcessed();
}
