package net.unixcode.rts.parser.compiler.xml.settings;

import net.unixcode.rts.parser.api.compiler.xml.IXMLSettings;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.net.URI;

@Component
public class SoundXMLSettings implements IXMLSettings {
  @Value("${xml.sound.root}")
  protected String root;

  @Value("${xml.sound.xslt}")
  protected String xslt;

  @Value("${xml.sound.namespace}")
  protected URI namespace;

  @Value("${xml.sound.schemaLocation}")
  protected URI schemaLocation;

  @Override
  public String getRootElementName() {
    return root;
  }

  @Override
  public String getXslt() {
    return xslt;
  }

  @Override
  public String getNamespace() {
    return namespace.toString();
  }

  @Override
  public String getSchemaLocation() {
    return schemaLocation.toString();
  }
}
