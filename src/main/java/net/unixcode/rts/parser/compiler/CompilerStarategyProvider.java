package net.unixcode.rts.parser.compiler;

import net.unixcode.rts.parser.api.compiler.CompilerType;
import net.unixcode.rts.parser.api.compiler.ICompilerStrategy;
import net.unixcode.rts.parser.api.compiler.ICompilerStrategyProvider;
import net.unixcode.rts.parser.compiler.antlr.stf.STFCompilerStrategy;
import net.unixcode.rts.parser.compiler.xml.XMLCompilerStrategy;
import org.springframework.stereotype.Component;

@Component
public class CompilerStarategyProvider implements ICompilerStrategyProvider {
  protected STFCompilerStrategy stfCompilerStrategy;
  protected XMLCompilerStrategy xmlCompilerStrategy;

  CompilerStarategyProvider(STFCompilerStrategy stfCompilerStrategy, XMLCompilerStrategy xmlCompilerStrategy) {
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
