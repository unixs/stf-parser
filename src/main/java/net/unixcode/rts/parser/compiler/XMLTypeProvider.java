package net.unixcode.rts.parser.compiler;

import net.unixcode.rts.parser.api.compiler.ISourceItem;
import net.unixcode.rts.parser.api.compiler.xml.IXMLTypeMapper;
import net.unixcode.rts.parser.api.compiler.xml.XMLType;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

@Component
public class XMLTypeProvider implements net.unixcode.rts.parser.api.compiler.xml.IXMLTypeProvider {
  protected IXMLTypeMapper xmlTypeMapper;

  public XMLTypeProvider(IXMLTypeMapper xmlTypeMapper) {
    this.xmlTypeMapper = xmlTypeMapper;
  }

  @Override
  public XMLType apply(@NotNull ISourceItem sourceItem) {
    try {
      var factory = DocumentBuilderFactory.newInstance();
      factory.setNamespaceAware(true);

      var document = factory.newDocumentBuilder().parse(sourceItem.getSourcePath());

      var namespace = document.getDocumentElement().getNamespaceURI();

      return xmlTypeMapper.apply(namespace);
    } catch (ParserConfigurationException | SAXException | IOException e) {
      throw new RuntimeException(e);
    }
  }
}
