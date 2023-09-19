package net.unixcode.rts.parser.api;

import org.antlr.v4.runtime.CharStream;


public interface ISourceItem {
  CharStream getStream();
  void setStream(CharStream stream);
  String getSourcePath();
  // String getTargetPath();
  // String getTargetFileName();
  // String getFullTargetPath();
}
