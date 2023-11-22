package net.unixcode.rts.parser.api.compiler.xml;

public interface IXMLSettings {
  String XSI_NS = "http://www.w3.org/2001/XMLSchema-instance";

  String getNamespace();
  String getSchemaLocation();
  String getRootElementName();
  String getXslt();
}
