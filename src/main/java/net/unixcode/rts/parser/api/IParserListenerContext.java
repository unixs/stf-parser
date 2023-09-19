package net.unixcode.rts.parser.api;

public interface IParserListenerContext {
  IParserListenerContext setSourcePath(String path);
  String getSourcePath();
  String getExtensiion();
  boolean processed();
  void setProcessed();
}
