// Generated from stf.g4 by ANTLR 4.3

  package net.unixcode.rts.parser.antlr;

import org.antlr.v4.runtime.misc.NotNull;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link stfParser}.
 */
public interface stfListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link stfParser#nodeName}.
	 * @param ctx the parse tree
	 */
	void enterNodeName(@NotNull stfParser.NodeNameContext ctx);
	/**
	 * Exit a parse tree produced by {@link stfParser#nodeName}.
	 * @param ctx the parse tree
	 */
	void exitNodeName(@NotNull stfParser.NodeNameContext ctx);

	/**
	 * Enter a parse tree produced by {@link stfParser#node}.
	 * @param ctx the parse tree
	 */
	void enterNode(@NotNull stfParser.NodeContext ctx);
	/**
	 * Exit a parse tree produced by {@link stfParser#node}.
	 * @param ctx the parse tree
	 */
	void exitNode(@NotNull stfParser.NodeContext ctx);

	/**
	 * Enter a parse tree produced by {@link stfParser#number}.
	 * @param ctx the parse tree
	 */
	void enterNumber(@NotNull stfParser.NumberContext ctx);
	/**
	 * Exit a parse tree produced by {@link stfParser#number}.
	 * @param ctx the parse tree
	 */
	void exitNumber(@NotNull stfParser.NumberContext ctx);

	/**
	 * Enter a parse tree produced by {@link stfParser#stf}.
	 * @param ctx the parse tree
	 */
	void enterStf(@NotNull stfParser.StfContext ctx);
	/**
	 * Exit a parse tree produced by {@link stfParser#stf}.
	 * @param ctx the parse tree
	 */
	void exitStf(@NotNull stfParser.StfContext ctx);

	/**
	 * Enter a parse tree produced by {@link stfParser#nodeListDelim}.
	 * @param ctx the parse tree
	 */
	void enterNodeListDelim(@NotNull stfParser.NodeListDelimContext ctx);
	/**
	 * Exit a parse tree produced by {@link stfParser#nodeListDelim}.
	 * @param ctx the parse tree
	 */
	void exitNodeListDelim(@NotNull stfParser.NodeListDelimContext ctx);

	/**
	 * Enter a parse tree produced by {@link stfParser#string}.
	 * @param ctx the parse tree
	 */
	void enterString(@NotNull stfParser.StringContext ctx);
	/**
	 * Exit a parse tree produced by {@link stfParser#string}.
	 * @param ctx the parse tree
	 */
	void exitString(@NotNull stfParser.StringContext ctx);

	/**
	 * Enter a parse tree produced by {@link stfParser#heading}.
	 * @param ctx the parse tree
	 */
	void enterHeading(@NotNull stfParser.HeadingContext ctx);
	/**
	 * Exit a parse tree produced by {@link stfParser#heading}.
	 * @param ctx the parse tree
	 */
	void exitHeading(@NotNull stfParser.HeadingContext ctx);

	/**
	 * Enter a parse tree produced by {@link stfParser#nodeListItem}.
	 * @param ctx the parse tree
	 */
	void enterNodeListItem(@NotNull stfParser.NodeListItemContext ctx);
	/**
	 * Exit a parse tree produced by {@link stfParser#nodeListItem}.
	 * @param ctx the parse tree
	 */
	void exitNodeListItem(@NotNull stfParser.NodeListItemContext ctx);

	/**
	 * Enter a parse tree produced by {@link stfParser#term}.
	 * @param ctx the parse tree
	 */
	void enterTerm(@NotNull stfParser.TermContext ctx);
	/**
	 * Exit a parse tree produced by {@link stfParser#term}.
	 * @param ctx the parse tree
	 */
	void exitTerm(@NotNull stfParser.TermContext ctx);

	/**
	 * Enter a parse tree produced by {@link stfParser#nodeList}.
	 * @param ctx the parse tree
	 */
	void enterNodeList(@NotNull stfParser.NodeListContext ctx);
	/**
	 * Exit a parse tree produced by {@link stfParser#nodeList}.
	 * @param ctx the parse tree
	 */
	void exitNodeList(@NotNull stfParser.NodeListContext ctx);

	/**
	 * Enter a parse tree produced by {@link stfParser#word}.
	 * @param ctx the parse tree
	 */
	void enterWord(@NotNull stfParser.WordContext ctx);
	/**
	 * Exit a parse tree produced by {@link stfParser#word}.
	 * @param ctx the parse tree
	 */
	void exitWord(@NotNull stfParser.WordContext ctx);

	/**
	 * Enter a parse tree produced by {@link stfParser#space}.
	 * @param ctx the parse tree
	 */
	void enterSpace(@NotNull stfParser.SpaceContext ctx);
	/**
	 * Exit a parse tree produced by {@link stfParser#space}.
	 * @param ctx the parse tree
	 */
	void exitSpace(@NotNull stfParser.SpaceContext ctx);
}