package net.unixcode.rts.parser.parsers.xml;

import net.unixcode.rts.parser.api.IXMLSettingsProvider;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.net.URI;

@Component
public class DefaultXMLSettingsProvider implements IXMLSettingsProvider {
  @Value("${xml.cabin.xslt}")
  protected String cabinXslt;

  @Value("${xml.cabin.namespace}")
  protected URI cabinNamespace;

  @Value("${xml.cabin.schemaLocation}")
  protected URI cabinSchemaLocation;

  @Value("${xml.state.xslt}")
  protected String stateXslt;

  @Value("${xml.state.namespace}")
  protected URI stateNamespace;

  @Value("${xml.state.schemaLocation}")
  protected URI stateSchemaLocation;

  @Override
  public String getCabinRootElementName() {
    return "cabin";
  }

  @Override
  public String getCabinXslt() {
    return cabinXslt;
  }

  @Override
  public String getCabinNamespace() {
    return cabinNamespace.toString();
  }

  @Override
  public String getCabinSchemaLocation() {
    return cabinSchemaLocation.toString();
  }

  @Override
  public String getStateRootElementName() {
    return "state";
  }

  @Override
  public String getStateXslt() {
    return stateXslt;
  }

  @Override
  public String getStateNamespace() {
    return stateNamespace.toString();
  }

  @Override
  public String getStateSchemaLocation() {
    return stateSchemaLocation.toString();
  }
}
