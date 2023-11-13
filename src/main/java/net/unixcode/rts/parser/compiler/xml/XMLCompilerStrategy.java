package net.unixcode.rts.parser.compiler.xml;

import net.unixcode.rts.parser.api.compiler.CompilerType;
import net.unixcode.rts.parser.api.compiler.ISourceItem;
import net.unixcode.rts.parser.api.compiler.xml.IXMLSourceItem;
import net.unixcode.rts.parser.api.compiler.xml.IXMLTransformer;
import net.unixcode.rts.parser.api.compiler.xml.IXMLTransformerContext;
import net.unixcode.rts.parser.compiler.DefaultCompilerStrategy;
import net.unixcode.rts.parser.services.FileEmitter;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Lookup;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.io.*;

@Component
public class XMLCompilerStrategy extends DefaultCompilerStrategy {
  final Logger logger = LoggerFactory.getLogger(getClass());
  final private static Integer OUT_BUF_SIZE = 5000;
  final private static String CXX_EXTENSION = "hpp";
  final public static CompilerType COMPILER_TYPE = CompilerType.XML;
  final protected IXMLTransformer xmlTransformer;

  public XMLCompilerStrategy(@Qualifier("model_xml_transformer") IXMLTransformer xmlTransformer, @NotNull FileEmitter emitter) {
    super(emitter.setExtension(CXX_EXTENSION));
    this.xmlTransformer = xmlTransformer;
  }

  @Override
  public void accept(ISourceItem iSourceItem) {
    checkSourceItem(iSourceItem, COMPILER_TYPE);

    var sourceItem = (IXMLSourceItem) iSourceItem;

    transform(sourceItem);
  }

  protected void transform(@NotNull IXMLSourceItem sourceItem) {
    try {
      var context = lookupContext();

      try (var inputStream = readInputFile(sourceItem)) {
        xmlTransformer.accept(inputStream, context.getOutputStream());
        sourceItem.setContext(context);
      }
    } catch (Exception e) {
      logger.error("Unable to translate XML.");
      logger.error(e.getMessage());

      throw new RuntimeException(e);
    }
  }

  @Lookup
  protected IXMLTransformerContext lookupContext() {
    return null;
  }

  protected InputStream readInputFile(@NotNull ISourceItem sourceItem) throws FileNotFoundException {
    return new FileInputStream(sourceItem.getSourcePath());
  }

  protected InputStream inputStreamFromSourceItem(@NotNull ISourceItem sourceItem) {
    var outputStream = new ByteArrayOutputStream(OUT_BUF_SIZE);
    var outWriter = new OutputStreamWriter(outputStream);
    var context = sourceItem.getContext();

    context.writeToStream(outWriter);

    try {
      //try (var xmlBytesStream = IOUtils.toInputStream(xmlString, "UTF-8")) {
      try (var inputBytesStream = new ByteArrayInputStream(outputStream.toByteArray())) {
        return inputBytesStream;
      }
    } catch (Exception e) {
      logger.error("Unable to create inputStream from source item.");
      logger.error(e.getMessage());

      throw new RuntimeException(e);
    }
  }
}
