package net.unixcode.rts.parser.compiler;

import net.unixcode.rts.parser.api.ICompilerFactory;
import net.unixcode.rts.parser.api.compiler.ICompiler;
import org.springframework.beans.factory.annotation.Lookup;
import org.springframework.stereotype.Component;

@Component
public class CompilerFactory implements ICompilerFactory {
  @Override
  public ICompiler get() {
    return lookupCompiler();
  }

  @Lookup
  protected ICompiler lookupCompiler() {
    return null;
  }
}
