package net.unixcode.rts.parser.translator.antlr.stf;

import net.unixcode.rts.parser.api.IParserListenerContext;
import net.unixcode.rts.parser.api.compiler.antlr.stf.ISTF2XMLListenerCtxt;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
import java.io.OutputStreamWriter;

@Component
@Scope("prototype")
public class STF2XMLListenerContext implements ISTF2XMLListenerCtxt {
  final protected Logger log = LoggerFactory.getLogger(getClass());
  protected String sourcePath;
  protected Document doc;
  protected boolean processed = false;

  public STF2XMLListenerContext() {
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
  public IParserListenerContext setSourcePath(String path) {
    sourcePath = path;

    return this;
  }

  @Override
  public String getSourcePath() {
    return sourcePath;
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
  public void writeToStream(OutputStreamWriter streamWriter) {
    try {
      TransformerFactory transformerFactory = TransformerFactory.newInstance();
      Transformer transformer = transformerFactory.newTransformer();
      transformer.setOutputProperty(OutputKeys.INDENT, "yes");

      DOMSource source = new DOMSource(getDoc());


      StreamResult result = new StreamResult(streamWriter);

      transformer.transform(source, result);
    }
    catch (Exception e) {
      log.error("Unable to transform Document to xml");
      log.error(e.getMessage());

      throw new RuntimeException(e);
    }
  }

/*  @Override
  public String toString() {
    try {
      TransformerFactory transformerFactory = TransformerFactory.newInstance();
      Transformer transformer = transformerFactory.newTransformer();
      transformer.setOutputProperty(OutputKeys.INDENT, "yes");

      DOMSource source = new DOMSource(getDoc());

      StreamResult result = new StreamResult(streamWriter);

      transformer.transform(source, result);
    }
    catch (Exception e) {
      System.err.println("Unable transform Document to xml.");
      System.err.println(e.getMessage());

      throw new RuntimeException(e);
    }
  }*/
}


