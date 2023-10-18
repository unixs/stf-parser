package net.unixcode.rts.parser.api;

import java.util.function.Consumer;

@FunctionalInterface
public interface ITranslator<T> extends Consumer<T> {
}
