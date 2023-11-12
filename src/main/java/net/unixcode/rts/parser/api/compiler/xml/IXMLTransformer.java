package net.unixcode.rts.parser.api.compiler.xml;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.function.BiConsumer;

@FunctionalInterface
public interface IXMLTransformer extends BiConsumer<InputStream, OutputStream> {
}
