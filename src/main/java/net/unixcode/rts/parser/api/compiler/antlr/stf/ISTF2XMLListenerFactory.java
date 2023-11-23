package net.unixcode.rts.parser.api.compiler.antlr.stf;

import net.unixcode.rts.parser.compiler.antlr.stf.STF2XMLListener;

import java.util.function.Function;

@FunctionalInterface
public interface ISTF2XMLListenerFactory extends Function<ISTFSourceItem, STF2XMLListener> {
}
