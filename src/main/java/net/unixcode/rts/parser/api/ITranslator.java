package net.unixcode.rts.parser.api;

import java.util.function.Consumer;
import java.util.function.Function;

@FunctionalInterface
public interface ITranslator<T> extends Consumer<T> {
}
