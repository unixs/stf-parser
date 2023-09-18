package net.unixcode.rts.parser.api;

import java.util.function.Consumer;

@FunctionalInterface
public interface IParserEmitter extends Consumer<IParserListenerContext> {
}
