package net.unixcode.rts.parser.compiler.xml.settings;

import net.unixcode.rts.parser.api.compiler.xml.IXMLSettings;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.net.URI;

@Component
public class CabinXMLSettings implements IXMLSettings {
  @Value("${xml.cabin.root}")
  protected String root;

  @Value("${xml.cabin.xslt}")
  protected String cabinXslt;

  @Value("${xml.cabin.namespace}")
  protected URI cabinNamespace;

  @Value("${xml.cabin.schemaLocation}")
  protected URI cabinSchemaLocation;

  @Override
  public String getRootElementName() {
    return root;
  }

  @Override
  public String getXslt() {
    return cabinXslt;
  }

  @Override
  public String getNamespace() {
    return cabinNamespace.toString();
  }

  @Override
  public String getSchemaLocation() {
    return cabinSchemaLocation.toString();
  }
}
