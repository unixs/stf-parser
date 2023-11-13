package net.unixcode.rts.parser.api.compiler.xml;

import net.unixcode.rts.parser.api.compiler.ICompilerContext;

import java.io.OutputStream;

public interface IXMLTransformerContext extends ICompilerContext {
  OutputStream getOutputStream();
}
