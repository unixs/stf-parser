package net.unixcode.rts.parser.compiler.xml;

import net.unixcode.rts.parser.api.compiler.CompilerType;
import net.unixcode.rts.parser.api.compiler.ISourceItem;
import net.unixcode.rts.parser.api.compiler.xml.IXMLSourceItem;
import net.unixcode.rts.parser.api.compiler.xml.IXMLTransformerContext;
import net.unixcode.rts.parser.compiler.DefaultSourceItem;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.w3c.dom.Document;

public class XMLSourceItem extends DefaultSourceItem implements IXMLSourceItem {

  public XMLSourceItem(String sourcePath, CompilerType compilerType) {
    super(sourcePath, compilerType);
  }

  @Override
  public IXMLTransformerContext getContext() {
    return (IXMLTransformerContext) this.context;
  }

  public void setContext(@NotNull IXMLTransformerContext context) {
    this.context = context;
  }

  @Contract("_ -> new")
  public static @NotNull ISourceItem defaultSourceItemFactory(String srcPath) {
    return new XMLSourceItem(srcPath, CompilerType.XML);
  }
}
