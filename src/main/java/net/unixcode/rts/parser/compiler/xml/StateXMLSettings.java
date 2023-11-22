package net.unixcode.rts.parser.compiler.xml;

import net.unixcode.rts.parser.api.compiler.xml.IXMLSettings;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.net.URI;

@Component
public class StateXMLSettings implements IXMLSettings {
  @Value("${xml.state.root}")
  protected String root;

  @Value("${xml.state.xslt}")
  protected String stateXslt;

  @Value("${xml.state.namespace}")
  protected URI stateNamespace;

  @Value("${xml.state.schemaLocation}")
  protected URI stateSchemaLocation;

  @Override
  public String getRootElementName() {
    return root;
  }

  @Override
  public String getXslt() {
    return stateXslt;
  }

  @Override
  public String getNamespace() {
    return stateNamespace.toString();
  }

  @Override
  public String getSchemaLocation() {
    return stateSchemaLocation.toString();
  }
}
