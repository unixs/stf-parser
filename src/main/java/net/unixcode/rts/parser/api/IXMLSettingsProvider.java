package net.unixcode.rts.parser.api;

public interface IXMLSettingsProvider {
  String XSI_NS = "http://www.w3.org/2001/XMLSchema-instance";

  String getCabinRootElementName();
  String getCabinXslt();
  String getCabinNamespace();
  String getCabinSchemaLocation();
  String getStateRootElementName();
  String getStateXslt();
  String getStateNamespace();
  String getStateSchemaLocation();
}
