package net.unixcode.rts.parser.compiler.xml;

import net.unixcode.rts.parser.api.compiler.xml.IXMLTransformerContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;

@Component
@Scope(BeanDefinition.SCOPE_PROTOTYPE)
public class XMLTransformerContext implements IXMLTransformerContext {
  final protected Logger log = LoggerFactory.getLogger(getClass());

  protected OutputStream outputStream;

  public XMLTransformerContext() {
    outputStream = new ByteArrayOutputStream();
  }

  @Override
  public OutputStream getOutputStream() {
    return outputStream;
  }

  @Override
  public void accept(OutputStreamWriter streamWriter) {
    try {
      streamWriter.write(outputStream.toString());
    }
    catch (Exception e) {
      log.error("Unable to write result.");
      log.error(e.getMessage());

      throw new RuntimeException(e);
    }
  }
}
