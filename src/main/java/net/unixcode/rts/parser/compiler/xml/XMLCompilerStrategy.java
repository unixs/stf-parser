package net.unixcode.rts.parser.compiler.xml;

import net.unixcode.rts.parser.api.compiler.CompilerType;
import net.unixcode.rts.parser.api.compiler.ISourceItem;
import net.unixcode.rts.parser.api.compiler.xml.*;
import net.unixcode.rts.parser.compiler.DefaultCompilerStrategy;
import net.unixcode.rts.parser.services.FileEmitter;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Lookup;
import org.springframework.stereotype.Component;


@Component
public class XMLCompilerStrategy extends DefaultCompilerStrategy {
  final Logger logger = LoggerFactory.getLogger(getClass());
  final private static String CXX_EXTENSION = "hpp";
  final public static CompilerType COMPILER_TYPE = CompilerType.XML;
  protected IXMLTransformerProvider transformerProvider;
  protected IXMLTypeProvider xmlTypeProvider;

  public XMLCompilerStrategy(IXMLTransformerProvider transformerProvider, IXMLTypeProvider xmlTypeProvider, @NotNull FileEmitter emitter) {
    super(emitter.setExtension(CXX_EXTENSION));
    this.transformerProvider = transformerProvider;
    this.xmlTypeProvider = xmlTypeProvider;
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

      try (var inputStream = sourceItem.getInputStream()) {
        var transformer = transformerProvider.apply(sourceItem.getType());

        transformer.accept(inputStream, context.getOutputStream());
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
}
