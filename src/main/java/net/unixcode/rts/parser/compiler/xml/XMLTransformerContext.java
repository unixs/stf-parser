package net.unixcode.rts.parser.compiler.xml;

import net.unixcode.rts.parser.api.compiler.xml.IXMLTransformerContext;
import net.unixcode.rts.parser.compiler.DefaultCompilerContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.io.*;

@Component
@Scope(BeanDefinition.SCOPE_PROTOTYPE)
public class XMLTransformerContext extends DefaultCompilerContext implements IXMLTransformerContext {
  final protected Logger log = LoggerFactory.getLogger(getClass());
  final private static Integer OUT_BUF_SIZE = 10000;

  protected ByteArrayOutputStream outputStream;

  public XMLTransformerContext() {
    outputStream = new ByteArrayOutputStream(OUT_BUF_SIZE);
  }

  @Override
  public OutputStream getOutputStream() {
    return outputStream;
  }

  @Override
  public void accept(OutputStream emitOutputStream) {
    try {
        outputStream.writeTo(emitOutputStream);
    }
    catch (Exception e) {
      log.error("Unable to write result.");
      log.error(e.getMessage());

      throw new RuntimeException(e);
    }
  }
}
