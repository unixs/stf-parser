package net.unixcode.rts.parser.translator;

import net.unixcode.rts.parser.api.ITranslator;
import net.unixcode.rts.parser.api.stf.ISTF2XMLListenerCtxt;
import org.apache.commons.io.FilenameUtils;
import org.springframework.stereotype.Component;
import org.springframework.util.ResourceUtils;
import org.w3c.dom.Document;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import java.io.*;

@Component
public class XML2CXXTranslator implements ITranslator<ISTF2XMLListenerCtxt> {
  public static final String XSLT_FILENAME = "classpath:xml/xml2cxx.xsl";
  protected Transformer transformer;

  public XML2CXXTranslator() {
    var transformerFactory = TransformerFactory.newInstance();

    try {
      File file = ResourceUtils.getFile(XSLT_FILENAME);

      transformer = transformerFactory.newTransformer(
        new StreamSource(file)
      );
    }
    catch (Exception e) {
      System.err.println("Unable to initialize XSLT transformer.");
      System.err.println(e.getMessage());

      throw new RuntimeException(e);
    }
  }

  @Override
  public void accept(ISTF2XMLListenerCtxt context) {
    try {

      var srcPath = context.getSourcePath();
      var filename =  FilenameUtils.removeExtension(srcPath) + FilenameUtils.EXTENSION_SEPARATOR + "cc";
      var output = new FileOutputStream(filename);

      transform(context.getDoc(), output);
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  protected void transform(Document doc, OutputStream output)
    throws TransformerException {
    transformer.transform(new DOMSource(doc), new StreamResult(output));
  }
}
