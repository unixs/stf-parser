package net.unixcode.rts.parser.translator;

import net.unixcode.rts.parser.api.compiler.CompilerType;
import net.unixcode.rts.parser.api.compiler.ICompilerStrategy;
import net.unixcode.rts.parser.api.compiler.ICompilerStrategyFactoryProvider;
import net.unixcode.rts.parser.translator.antlr.stf.STFCompilerStrategy;
import net.unixcode.rts.parser.translator.xml.XMLCompilerStrategy;
import org.springframework.stereotype.Component;

@Component
public class CompilerStarategyFactoryProvider implements ICompilerStrategyFactoryProvider {
  protected STFCompilerStrategy stfCompilerStrategy;
  protected XMLCompilerStrategy xmlCompilerStrategy;

  CompilerStarategyFactoryProvider(STFCompilerStrategy stfCompilerStrategy, XMLCompilerStrategy xmlCompilerStrategy) {
    this.stfCompilerStrategy = stfCompilerStrategy;
    this.xmlCompilerStrategy = xmlCompilerStrategy;
  }

  @Override
  public ICompilerStrategy apply(CompilerType compilerType) {
    return switch (compilerType) {
      case STF -> stfCompilerStrategy;
      case XML -> xmlCompilerStrategy;
    };
  }
}
