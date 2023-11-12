package net.unixcode.rts.parser.compiler.xml;

import net.unixcode.rts.parser.api.compiler.CompilerType;
import net.unixcode.rts.parser.api.compiler.ISourceItem;
import net.unixcode.rts.parser.api.compiler.xml.IXMLSourceItem;
import net.unixcode.rts.parser.compiler.DefaultCompilerStrategy;
import net.unixcode.rts.parser.services.FileEmitter;
import org.springframework.stereotype.Component;

@Component
public class XMLCompilerStrategy extends DefaultCompilerStrategy {
  final public static CompilerType COMPILER_TYPE = CompilerType.XML;

  public XMLCompilerStrategy(FileEmitter emitter) {
    super(emitter);
  }

  @Override
  public void accept(ISourceItem iSourceItem) {
    checkSourceItem(iSourceItem, COMPILER_TYPE);

    var sourceItem = (IXMLSourceItem) iSourceItem;
  }
}
