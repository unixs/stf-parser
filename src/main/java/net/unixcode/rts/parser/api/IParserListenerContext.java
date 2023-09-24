package net.unixcode.rts.parser.api;

import java.io.FileWriter;
import java.io.OutputStreamWriter;

public interface IParserListenerContext {
  IParserListenerContext setSourcePath(String path);
  String getSourcePath();
  String getFileExtensiion();
  boolean processed();
  void setProcessed();

  void writeToStream(OutputStreamWriter writer);
}
