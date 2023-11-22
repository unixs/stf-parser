package net.unixcode.rts.parser.api.compiler;

public interface ITypedSourceItem<T> extends ISourceItem {
  T getType();
}
