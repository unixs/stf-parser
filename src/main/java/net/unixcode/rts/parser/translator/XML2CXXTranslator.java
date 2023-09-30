package net.unixcode.rts.parser.translator;

import net.sf.saxon.s9api.*;
import net.unixcode.rts.parser.api.ITranslator;
import net.unixcode.rts.parser.api.stf.ISTF2XMLListenerCtxt;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import org.xml.sax.InputSource;

import javax.xml.transform.sax.SAXSource;
import javax.xml.transform.stream.StreamSource;
import java.io.*;

@Component
public class XML2CXXTranslator implements ITranslator<ISTF2XMLListenerCtxt> {
  final Logger logger = LoggerFactory.getLogger(getClass());
  public static final String XSLT_FILENAME = "classpath:xml/xml2cxx.xsl";
  public static final String CXX_EXTENSION = "cc";
  protected Processor processor;
  protected XsltExecutable stylesheet;
  protected Xslt30Transformer transformer;

  public XML2CXXTranslator(ApplicationContext appContext) {
    try {
      InputStream xsltInputStream = appContext.getResource(XSLT_FILENAME).getInputStream();

      processor = new Processor(false);
      XsltCompiler compiler = processor.newXsltCompiler();

      stylesheet = compiler.compile(new StreamSource(xsltInputStream));

      transformer = stylesheet.load30();
    }
    catch (Exception e) {
      logger.error("Unable to initialize XSLT transformer.");
      logger.error(e.getMessage());

      throw new RuntimeException(e);
    }
  }

  @Override
  public void accept(ISTF2XMLListenerCtxt context) {
    var outputStream = new ByteArrayOutputStream(5000000);
    var outWriter = new OutputStreamWriter(outputStream);
    context.writeToStream(outWriter);
    var xmlString = outputStream.toString();

    try {
      try (var inputStream = IOUtils.toInputStream(xmlString, "UTF-8")) {
        var inputSource = new InputSource(inputStream);
        var saxSource = new SAXSource(inputSource);

        var srcPath = context.getSourcePath();
        var outPath =  FilenameUtils.removeExtension(srcPath) + FilenameUtils.EXTENSION_SEPARATOR + CXX_EXTENSION;
        var outFile = new File(outPath);

        transform(saxSource, outFile);
      }
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  protected void transform(SAXSource source, File to)
    throws SaxonApiException {
    Serializer output = processor.newSerializer(to);
    output.setOutputProperty(Serializer.Property.METHOD, "text");

    transformer.transform(source, output);
  }
}
