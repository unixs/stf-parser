package net.unixcode.rts.parser.translator.xml;

import net.unixcode.rts.parser.api.ITranslator;
import net.unixcode.rts.parser.api.compiler.xml.IXMLTransformer;
import net.unixcode.rts.parser.api.compiler.antlr.stf.ISTF2XMLListenerCtxt;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.io.*;

@Component
final public class CabinXML2CXXTranslator implements ITranslator<ISTF2XMLListenerCtxt> {
  final Logger logger = LoggerFactory.getLogger(getClass());

  private final IXMLTransformer xmlTransformer;
  private static final Integer OUT_BUF_SIZE = 5000;
  private static final String CXX_EXTENSION = "hpp";

  public CabinXML2CXXTranslator(@Qualifier("model_xml_transformer") IXMLTransformer xmlTransformer) {
    this.xmlTransformer = xmlTransformer;
  }

  @Override
  public void accept(ISTF2XMLListenerCtxt context) {
    var outputStream = new ByteArrayOutputStream(OUT_BUF_SIZE);
    var outWriter = new OutputStreamWriter(outputStream);
    context.writeToStream(outWriter);
    var xmlString = outputStream.toString();

    try {
      try (var inputStream = IOUtils.toInputStream(xmlString, "UTF-8")) {
        var srcPath = context.getSourcePath();
        var outPath =  FilenameUtils.removeExtension(srcPath) + FilenameUtils.EXTENSION_SEPARATOR + CXX_EXTENSION;

        var outStream = new FileOutputStream(outPath);

        xmlTransformer.accept(inputStream, outStream);
      }
    } catch (Exception e) {
      logger.error("Unable to translate cabin XML to CXX.");
      logger.error(e.getMessage());

      throw new RuntimeException(e);
    }
  }
}
