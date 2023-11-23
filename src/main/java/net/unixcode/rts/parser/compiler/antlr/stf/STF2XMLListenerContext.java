package net.unixcode.rts.parser.compiler.antlr.stf;

import net.unixcode.rts.parser.api.compiler.ISTF2XMLTypeMapper;
import net.unixcode.rts.parser.api.compiler.antlr.stf.ISTF2XMLListenerCtxt;
import net.unixcode.rts.parser.api.compiler.antlr.stf.ISTF2XMLSettingsProvider;
import net.unixcode.rts.parser.api.compiler.antlr.stf.ISTFSourceItem;
import net.unixcode.rts.parser.api.compiler.antlr.stf.STFType;
import net.unixcode.rts.parser.api.compiler.xml.IXMLSettings;
import net.unixcode.rts.parser.api.compiler.xml.XMLType;
import net.unixcode.rts.parser.compiler.DefaultCompilerContext;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.w3c.dom.Document;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.OutputStream;

@Component
@Scope(BeanDefinition.SCOPE_PROTOTYPE)
public class STF2XMLListenerContext extends DefaultCompilerContext implements ISTF2XMLListenerCtxt {
  final protected Logger log = LoggerFactory.getLogger(getClass());
  protected Document doc;
  protected boolean processed = false;
  protected IXMLSettings xmlSettings;
  protected ISTF2XMLTypeMapper typeMapper;
  protected ISTF2XMLSettingsProvider settingsProvider;

  public STF2XMLListenerContext(@NotNull ISTF2XMLSettingsProvider settingsProvider, @NotNull ISTF2XMLTypeMapper typeMapper) {
    this.typeMapper = typeMapper;
    this.settingsProvider = settingsProvider;

    try {
      DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
      dbf.setNamespaceAware(true);

      DocumentBuilder builder = dbf.newDocumentBuilder();
      doc = builder.newDocument();
    }
    catch (ParserConfigurationException e) {
      log.error(e.getMessage());

      throw new RuntimeException(e);
    }
  }

  @Override
  public boolean processed() {
    return processed;
  }

  @Override
  public void setProcessed() {
    processed = true;
  }

  @Override
  public Document getDoc() {
    return doc;
  }



  @Override
  public IXMLSettings getXMLSettings() {
    if (xmlSettings == null) {
      if (sourceItem == null) {
        String msg = "Source item is null. Try to call specific setter before compilation.";
        log.error(msg);

        throw new IllegalArgumentException(msg);
      }

      ISTFSourceItem sourceItem = (ISTFSourceItem) getSourceItem();
      var xmlType = typeMapper.apply(sourceItem.getType());

      xmlSettings = settingsProvider.apply(xmlType);
    }

    return xmlSettings;
  }

  @Override
  public void accept(OutputStream emitOutputStream) {
    try {
      TransformerFactory transformerFactory = TransformerFactory.newInstance();
      Transformer transformer = transformerFactory.newTransformer();
      transformer.setOutputProperty(OutputKeys.INDENT, "yes");

      DOMSource source = new DOMSource(getDoc());


      StreamResult result = new StreamResult(emitOutputStream);

      transformer.transform(source, result);
    }
    catch (Exception e) {
      log.error("Unable to transform Document to xml");
      log.error(e.getMessage());

      throw new RuntimeException(e);
    }
  }
}


