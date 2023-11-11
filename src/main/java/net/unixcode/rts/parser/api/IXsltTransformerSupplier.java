package net.unixcode.rts.parser.api;

import net.sf.saxon.s9api.AbstractXsltTransformer;
import net.sf.saxon.s9api.Xslt30Transformer;

import java.io.InputStream;
import java.util.function.Function;

public interface IXsltTransformerSupplier extends Function<InputStream, Xslt30Transformer> {
}
