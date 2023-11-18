package net.unixcode.rts.parser.compiler.xml;

import net.sf.saxon.s9api.*;
import net.unixcode.rts.parser.api.compiler.xml.IXMLTransformer;
import net.unixcode.rts.parser.api.compiler.xml.IXsltTransformerSupplier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.xml.sax.InputSource;

import javax.xml.transform.sax.SAXSource;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;


public class DefaultXMLTransformer implements IXMLTransformer {
  final Logger logger = LoggerFactory.getLogger(getClass());

  Xslt30Transformer transformer;

  public DefaultXMLTransformer(ApplicationContext appContext, IXsltTransformerSupplier transformerSupplier, String xsltPath) {
    try {
      var xsltInputStream = appContext.getResource(xsltPath).getInputStream();

      transformer = transformerSupplier.apply(xsltInputStream);
    }
    catch (Exception e) {
      logger.error("Unable to initialize default XSLT translator.");
      logger.error(e.getMessage());

      throw new RuntimeException(e);
    }
  }

  @Override
  public void accept(InputStream xmlStream, OutputStream outputStream) {
    var inputSource = new InputSource(new InputStreamReader(xmlStream, StandardCharsets.UTF_8));
    var saxSource = new SAXSource(inputSource);

    var dst = transformer.newSerializer(outputStream);

    try {
      transformer.transform(saxSource, dst);
    } catch (SaxonApiException e) {
      throw new RuntimeException(e);
    }
  }
}
