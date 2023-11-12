package net.unixcode.rts.parser.translator.xml;

import net.unixcode.rts.parser.api.compiler.CompilerType;
import net.unixcode.rts.parser.api.compiler.ISourceItem;
import net.unixcode.rts.parser.api.compiler.xml.IXMLSourceItem;
import net.unixcode.rts.parser.translator.DefaultCompilerStrategy;
import org.springframework.stereotype.Component;

@Component
public class XMLCompilerStrategy extends DefaultCompilerStrategy {
  final public static CompilerType COMPILER_TYPE = CompilerType.XML;

  @Override
  public void accept(ISourceItem iSourceItem) {
    checkSourceItem(iSourceItem, COMPILER_TYPE);

    var sourceItem = (IXMLSourceItem) iSourceItem;
  }
}
