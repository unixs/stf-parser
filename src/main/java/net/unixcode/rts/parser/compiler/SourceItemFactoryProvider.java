package net.unixcode.rts.parser.compiler;

import net.unixcode.rts.parser.api.compiler.CompilerType;
import net.unixcode.rts.parser.api.compiler.ISourceItemFactory;
import net.unixcode.rts.parser.api.compiler.ISourceItemFactoryProvider;
import net.unixcode.rts.parser.api.compiler.antlr.stf.ISTFSourceItemFactory;
import net.unixcode.rts.parser.api.compiler.xml.IXMLSourceItemFactory;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;

@Component
public class SourceItemFactoryProvider implements ISourceItemFactoryProvider {
  protected ISTFSourceItemFactory stfSourceItemFactory;
  protected IXMLSourceItemFactory xmlSourceItemFactory;

  SourceItemFactoryProvider(ISTFSourceItemFactory stfSourceItemFactory, IXMLSourceItemFactory xmlSourceItemFactory) {
    this.stfSourceItemFactory = stfSourceItemFactory;
    this.xmlSourceItemFactory = xmlSourceItemFactory;
  }

  @Override
  public ISourceItemFactory apply(@NotNull CompilerType compilerType) {
    return switch (compilerType) {
      case STF -> stfSourceItemFactory;
      case XML -> xmlSourceItemFactory;
    };
  }
}
