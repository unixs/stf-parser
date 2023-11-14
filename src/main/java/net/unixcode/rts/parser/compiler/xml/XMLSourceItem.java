package net.unixcode.rts.parser.compiler.xml;

import net.unixcode.rts.parser.api.compiler.CompilerType;
import net.unixcode.rts.parser.api.compiler.ISourceItem;
import net.unixcode.rts.parser.api.compiler.xml.IXMLSourceItem;
import net.unixcode.rts.parser.api.compiler.xml.IXMLTransformerContext;
import net.unixcode.rts.parser.api.compiler.xml.IXMLTypeProvider;
import net.unixcode.rts.parser.api.compiler.xml.XMLType;
import net.unixcode.rts.parser.compiler.DefaultSourceItem;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

public class XMLSourceItem extends DefaultSourceItem implements IXMLSourceItem {
  protected XMLType xmlType;

  public XMLSourceItem(@NotNull IXMLTypeProvider xmlTypeProvider, String sourcePath, CompilerType compilerType) {
    super(sourcePath, compilerType);
    this.xmlType = xmlTypeProvider.apply(this);
  }

  @Override
  public IXMLTransformerContext getContext() {
    return (IXMLTransformerContext) this.context;
  }

  @Override
  public XMLType getType() {
    return xmlType;
  }

  public void setContext(@NotNull IXMLTransformerContext context) {
    this.context = context;
  }

  public static @NotNull ISourceItem defaultSourceItemFactory(IXMLTypeProvider xmlTypeProvider, String srcPath) {
    return new XMLSourceItem(xmlTypeProvider, srcPath, CompilerType.XML);
  }
}
