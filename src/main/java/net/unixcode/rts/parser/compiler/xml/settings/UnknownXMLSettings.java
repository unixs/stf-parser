package net.unixcode.rts.parser.compiler.xml.settings;

import net.unixcode.rts.parser.api.compiler.xml.IXMLSettings;
import org.springframework.stereotype.Component;

@Component
public class UnknownXMLSettings implements IXMLSettings {
  @Override
  public String getNamespace() {
    return null;
  }

  @Override
  public String getSchemaLocation() {
    return null;
  }

  @Override
  public String getRootElementName() {
    return "root";
  }

  @Override
  public String getXslt() {
    return null;
  }
}
