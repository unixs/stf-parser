// Generated from stf.g4 by ANTLR 4.3

  package net.unixcode.rts.parser.antlr;

import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class stfParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.3", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		MAGIC_HEADING_1=1, LEFT_PAREN=2, RIGHT_PAREN=3, STRING=4, SYMBOL=5, LEFT_BRACKET=6, 
		RIGHT_BRACKET=7, PLUS=8, ANY_LETTER=9, CAPITAL_LETTER=10, LETTER=11, CYR_LETTER=12, 
		DIGIT=13, DASH=14, DOT=15, WS=16, NEWLINE=17;
	public static final String[] tokenNames = {
		"<INVALID>", "'SIMISA@@@@@@@@@@JINX0t1t______'", "'('", "')'", "STRING", 
		"SYMBOL", "'['", "']'", "'+'", "ANY_LETTER", "CAPITAL_LETTER", "LETTER", 
		"CYR_LETTER", "DIGIT", "'-'", "'.'", "WS", "NEWLINE"
	};
	public static final int
		RULE_stf = 0, RULE_heading = 1, RULE_node = 2, RULE_space = 3, RULE_nodeName = 4, 
		RULE_nodeList = 5, RULE_nodeListDelim = 6, RULE_nodeListItem = 7, RULE_term = 8, 
		RULE_string = 9, RULE_word = 10, RULE_number = 11;
	public static final String[] ruleNames = {
		"stf", "heading", "node", "space", "nodeName", "nodeList", "nodeListDelim", 
		"nodeListItem", "term", "string", "word", "number"
	};

	@Override
	public String getGrammarFileName() { return "stf.g4"; }

	@Override
	public String[] getTokenNames() { return tokenNames; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public stfParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}
	public static class StfContext extends ParserRuleContext {
		public TerminalNode EOF() { return getToken(stfParser.EOF, 0); }
		public List<SpaceContext> space() {
			return getRuleContexts(SpaceContext.class);
		}
		public HeadingContext heading() {
			return getRuleContext(HeadingContext.class,0);
		}
		public SpaceContext space(int i) {
			return getRuleContext(SpaceContext.class,i);
		}
		public NodeContext node(int i) {
			return getRuleContext(NodeContext.class,i);
		}
		public List<NodeContext> node() {
			return getRuleContexts(NodeContext.class);
		}
		public StfContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_stf; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof stfListener ) ((stfListener)listener).enterStf(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof stfListener ) ((stfListener)listener).exitStf(this);
		}
	}

	public final StfContext stf() throws RecognitionException {
		StfContext _localctx = new StfContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_stf);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(24); heading();
			setState(26);
			_la = _input.LA(1);
			if (_la==WS) {
				{
				setState(25); space();
				}
			}

			setState(28); node();
			setState(30);
			switch ( getInterpreter().adaptivePredict(_input,1,_ctx) ) {
			case 1:
				{
				setState(29); space();
				}
				break;
			}
			setState(35);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==ANY_LETTER) {
				{
				{
				setState(32); node();
				}
				}
				setState(37);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(39);
			_la = _input.LA(1);
			if (_la==WS) {
				{
				setState(38); space();
				}
			}

			setState(41); match(EOF);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class HeadingContext extends ParserRuleContext {
		public TerminalNode MAGIC_HEADING_1() { return getToken(stfParser.MAGIC_HEADING_1, 0); }
		public HeadingContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_heading; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof stfListener ) ((stfListener)listener).enterHeading(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof stfListener ) ((stfListener)listener).exitHeading(this);
		}
	}

	public final HeadingContext heading() throws RecognitionException {
		HeadingContext _localctx = new HeadingContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_heading);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(43); match(MAGIC_HEADING_1);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class NodeContext extends ParserRuleContext {
		public TerminalNode RIGHT_PAREN() { return getToken(stfParser.RIGHT_PAREN, 0); }
		public NodeNameContext nodeName() {
			return getRuleContext(NodeNameContext.class,0);
		}
		public NodeListContext nodeList() {
			return getRuleContext(NodeListContext.class,0);
		}
		public List<SpaceContext> space() {
			return getRuleContexts(SpaceContext.class);
		}
		public SpaceContext space(int i) {
			return getRuleContext(SpaceContext.class,i);
		}
		public TerminalNode LEFT_PAREN() { return getToken(stfParser.LEFT_PAREN, 0); }
		public NodeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_node; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof stfListener ) ((stfListener)listener).enterNode(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof stfListener ) ((stfListener)listener).exitNode(this);
		}
	}

	public final NodeContext node() throws RecognitionException {
		NodeContext _localctx = new NodeContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_node);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(45); nodeName();
			setState(47);
			_la = _input.LA(1);
			if (_la==WS) {
				{
				setState(46); space();
				}
			}

			setState(49); match(LEFT_PAREN);
			setState(51);
			switch ( getInterpreter().adaptivePredict(_input,5,_ctx) ) {
			case 1:
				{
				setState(50); space();
				}
				break;
			}
			setState(54);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << STRING) | (1L << SYMBOL) | (1L << ANY_LETTER) | (1L << CYR_LETTER) | (1L << DIGIT) | (1L << DASH))) != 0)) {
				{
				setState(53); nodeList();
				}
			}

			setState(57);
			_la = _input.LA(1);
			if (_la==WS) {
				{
				setState(56); space();
				}
			}

			setState(59); match(RIGHT_PAREN);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class SpaceContext extends ParserRuleContext {
		public TerminalNode WS(int i) {
			return getToken(stfParser.WS, i);
		}
		public List<TerminalNode> WS() { return getTokens(stfParser.WS); }
		public SpaceContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_space; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof stfListener ) ((stfListener)listener).enterSpace(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof stfListener ) ((stfListener)listener).exitSpace(this);
		}
	}

	public final SpaceContext space() throws RecognitionException {
		SpaceContext _localctx = new SpaceContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_space);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(62); 
			_errHandler.sync(this);
			_alt = 1;
			do {
				switch (_alt) {
				case 1:
					{
					{
					setState(61); match(WS);
					}
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				setState(64); 
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,8,_ctx);
			} while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER );
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class NodeNameContext extends ParserRuleContext {
		public List<TerminalNode> ANY_LETTER() { return getTokens(stfParser.ANY_LETTER); }
		public TerminalNode ANY_LETTER(int i) {
			return getToken(stfParser.ANY_LETTER, i);
		}
		public TerminalNode DIGIT(int i) {
			return getToken(stfParser.DIGIT, i);
		}
		public List<TerminalNode> DIGIT() { return getTokens(stfParser.DIGIT); }
		public NodeNameContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_nodeName; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof stfListener ) ((stfListener)listener).enterNodeName(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof stfListener ) ((stfListener)listener).exitNodeName(this);
		}
	}

	public final NodeNameContext nodeName() throws RecognitionException {
		NodeNameContext _localctx = new NodeNameContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_nodeName);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(66); match(ANY_LETTER);
			setState(70);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==ANY_LETTER || _la==DIGIT) {
				{
				{
				setState(67);
				_la = _input.LA(1);
				if ( !(_la==ANY_LETTER || _la==DIGIT) ) {
				_errHandler.recoverInline(this);
				}
				consume();
				}
				}
				setState(72);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class NodeListContext extends ParserRuleContext {
		public List<NodeListItemContext> nodeListItem() {
			return getRuleContexts(NodeListItemContext.class);
		}
		public List<NodeListDelimContext> nodeListDelim() {
			return getRuleContexts(NodeListDelimContext.class);
		}
		public NodeListItemContext nodeListItem(int i) {
			return getRuleContext(NodeListItemContext.class,i);
		}
		public NodeListDelimContext nodeListDelim(int i) {
			return getRuleContext(NodeListDelimContext.class,i);
		}
		public NodeListContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_nodeList; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof stfListener ) ((stfListener)listener).enterNodeList(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof stfListener ) ((stfListener)listener).exitNodeList(this);
		}
	}

	public final NodeListContext nodeList() throws RecognitionException {
		NodeListContext _localctx = new NodeListContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_nodeList);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(73); nodeListItem();
			setState(79);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,10,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(74); nodeListDelim();
					setState(75); nodeListItem();
					}
					} 
				}
				setState(81);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,10,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class NodeListDelimContext extends ParserRuleContext {
		public SpaceContext space() {
			return getRuleContext(SpaceContext.class,0);
		}
		public NodeListDelimContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_nodeListDelim; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof stfListener ) ((stfListener)listener).enterNodeListDelim(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof stfListener ) ((stfListener)listener).exitNodeListDelim(this);
		}
	}

	public final NodeListDelimContext nodeListDelim() throws RecognitionException {
		NodeListDelimContext _localctx = new NodeListDelimContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_nodeListDelim);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(82); space();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class NodeListItemContext extends ParserRuleContext {
		public TermContext term() {
			return getRuleContext(TermContext.class,0);
		}
		public NodeContext node() {
			return getRuleContext(NodeContext.class,0);
		}
		public NodeListItemContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_nodeListItem; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof stfListener ) ((stfListener)listener).enterNodeListItem(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof stfListener ) ((stfListener)listener).exitNodeListItem(this);
		}
	}

	public final NodeListItemContext nodeListItem() throws RecognitionException {
		NodeListItemContext _localctx = new NodeListItemContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_nodeListItem);
		try {
			setState(86);
			switch ( getInterpreter().adaptivePredict(_input,11,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(84); node();
				}
				break;

			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(85); term();
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class TermContext extends ParserRuleContext {
		public WordContext word() {
			return getRuleContext(WordContext.class,0);
		}
		public NumberContext number() {
			return getRuleContext(NumberContext.class,0);
		}
		public StringContext string() {
			return getRuleContext(StringContext.class,0);
		}
		public TermContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_term; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof stfListener ) ((stfListener)listener).enterTerm(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof stfListener ) ((stfListener)listener).exitTerm(this);
		}
	}

	public final TermContext term() throws RecognitionException {
		TermContext _localctx = new TermContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_term);
		try {
			setState(91);
			switch (_input.LA(1)) {
			case SYMBOL:
			case ANY_LETTER:
			case CYR_LETTER:
				enterOuterAlt(_localctx, 1);
				{
				setState(88); word();
				}
				break;
			case DIGIT:
			case DASH:
				enterOuterAlt(_localctx, 2);
				{
				setState(89); number();
				}
				break;
			case STRING:
				enterOuterAlt(_localctx, 3);
				{
				setState(90); string();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class StringContext extends ParserRuleContext {
		public TerminalNode STRING(int i) {
			return getToken(stfParser.STRING, i);
		}
		public List<SpaceContext> space() {
			return getRuleContexts(SpaceContext.class);
		}
		public SpaceContext space(int i) {
			return getRuleContext(SpaceContext.class,i);
		}
		public List<TerminalNode> STRING() { return getTokens(stfParser.STRING); }
		public List<TerminalNode> PLUS() { return getTokens(stfParser.PLUS); }
		public TerminalNode PLUS(int i) {
			return getToken(stfParser.PLUS, i);
		}
		public StringContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_string; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof stfListener ) ((stfListener)listener).enterString(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof stfListener ) ((stfListener)listener).exitString(this);
		}
	}

	public final StringContext string() throws RecognitionException {
		StringContext _localctx = new StringContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_string);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(93); match(STRING);
			setState(95);
			switch ( getInterpreter().adaptivePredict(_input,13,_ctx) ) {
			case 1:
				{
				setState(94); space();
				}
				break;
			}
			setState(104);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==PLUS) {
				{
				{
				setState(97); match(PLUS);
				setState(99);
				_la = _input.LA(1);
				if (_la==WS) {
					{
					setState(98); space();
					}
				}

				setState(101); match(STRING);
				}
				}
				setState(106);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class WordContext extends ParserRuleContext {
		public TerminalNode ANY_LETTER(int i) {
			return getToken(stfParser.ANY_LETTER, i);
		}
		public TerminalNode CYR_LETTER(int i) {
			return getToken(stfParser.CYR_LETTER, i);
		}
		public List<TerminalNode> SYMBOL() { return getTokens(stfParser.SYMBOL); }
		public TerminalNode PLUS(int i) {
			return getToken(stfParser.PLUS, i);
		}
		public TerminalNode DOT(int i) {
			return getToken(stfParser.DOT, i);
		}
		public List<TerminalNode> DASH() { return getTokens(stfParser.DASH); }
		public TerminalNode SYMBOL(int i) {
			return getToken(stfParser.SYMBOL, i);
		}
		public List<TerminalNode> ANY_LETTER() { return getTokens(stfParser.ANY_LETTER); }
		public List<TerminalNode> DOT() { return getTokens(stfParser.DOT); }
		public TerminalNode DIGIT(int i) {
			return getToken(stfParser.DIGIT, i);
		}
		public List<TerminalNode> CYR_LETTER() { return getTokens(stfParser.CYR_LETTER); }
		public List<TerminalNode> DIGIT() { return getTokens(stfParser.DIGIT); }
		public List<TerminalNode> PLUS() { return getTokens(stfParser.PLUS); }
		public TerminalNode DASH(int i) {
			return getToken(stfParser.DASH, i);
		}
		public WordContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_word; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof stfListener ) ((stfListener)listener).enterWord(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof stfListener ) ((stfListener)listener).exitWord(this);
		}
	}

	public final WordContext word() throws RecognitionException {
		WordContext _localctx = new WordContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_word);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(108); 
			_errHandler.sync(this);
			_alt = 1;
			do {
				switch (_alt) {
				case 1:
					{
					{
					setState(107);
					_la = _input.LA(1);
					if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << SYMBOL) | (1L << ANY_LETTER) | (1L << CYR_LETTER))) != 0)) ) {
					_errHandler.recoverInline(this);
					}
					consume();
					}
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				setState(110); 
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,16,_ctx);
			} while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER );
			setState(115);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << SYMBOL) | (1L << PLUS) | (1L << ANY_LETTER) | (1L << CYR_LETTER) | (1L << DIGIT) | (1L << DASH) | (1L << DOT))) != 0)) {
				{
				{
				setState(112);
				_la = _input.LA(1);
				if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << SYMBOL) | (1L << PLUS) | (1L << ANY_LETTER) | (1L << CYR_LETTER) | (1L << DIGIT) | (1L << DASH) | (1L << DOT))) != 0)) ) {
				_errHandler.recoverInline(this);
				}
				consume();
				}
				}
				setState(117);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class NumberContext extends ParserRuleContext {
		public TerminalNode DOT() { return getToken(stfParser.DOT, 0); }
		public TerminalNode DIGIT(int i) {
			return getToken(stfParser.DIGIT, i);
		}
		public List<TerminalNode> DIGIT() { return getTokens(stfParser.DIGIT); }
		public TerminalNode DASH() { return getToken(stfParser.DASH, 0); }
		public NumberContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_number; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof stfListener ) ((stfListener)listener).enterNumber(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof stfListener ) ((stfListener)listener).exitNumber(this);
		}
	}

	public final NumberContext number() throws RecognitionException {
		NumberContext _localctx = new NumberContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_number);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(119);
			_la = _input.LA(1);
			if (_la==DASH) {
				{
				setState(118); match(DASH);
				}
			}

			setState(122); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(121); match(DIGIT);
				}
				}
				setState(124); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==DIGIT );
			setState(132);
			_la = _input.LA(1);
			if (_la==DOT) {
				{
				setState(126); match(DOT);
				setState(128); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(127); match(DIGIT);
					}
					}
					setState(130); 
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( _la==DIGIT );
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static final String _serializedATN =
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\3\23\u0089\4\2\t\2"+
		"\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13"+
		"\t\13\4\f\t\f\4\r\t\r\3\2\3\2\5\2\35\n\2\3\2\3\2\5\2!\n\2\3\2\7\2$\n\2"+
		"\f\2\16\2\'\13\2\3\2\5\2*\n\2\3\2\3\2\3\3\3\3\3\4\3\4\5\4\62\n\4\3\4\3"+
		"\4\5\4\66\n\4\3\4\5\49\n\4\3\4\5\4<\n\4\3\4\3\4\3\5\6\5A\n\5\r\5\16\5"+
		"B\3\6\3\6\7\6G\n\6\f\6\16\6J\13\6\3\7\3\7\3\7\3\7\7\7P\n\7\f\7\16\7S\13"+
		"\7\3\b\3\b\3\t\3\t\5\tY\n\t\3\n\3\n\3\n\5\n^\n\n\3\13\3\13\5\13b\n\13"+
		"\3\13\3\13\5\13f\n\13\3\13\7\13i\n\13\f\13\16\13l\13\13\3\f\6\fo\n\f\r"+
		"\f\16\fp\3\f\7\ft\n\f\f\f\16\fw\13\f\3\r\5\rz\n\r\3\r\6\r}\n\r\r\r\16"+
		"\r~\3\r\3\r\6\r\u0083\n\r\r\r\16\r\u0084\5\r\u0087\n\r\3\r\2\2\16\2\4"+
		"\6\b\n\f\16\20\22\24\26\30\2\5\4\2\13\13\17\17\5\2\7\7\13\13\16\16\5\2"+
		"\7\7\n\13\16\21\u0093\2\32\3\2\2\2\4-\3\2\2\2\6/\3\2\2\2\b@\3\2\2\2\n"+
		"D\3\2\2\2\fK\3\2\2\2\16T\3\2\2\2\20X\3\2\2\2\22]\3\2\2\2\24_\3\2\2\2\26"+
		"n\3\2\2\2\30y\3\2\2\2\32\34\5\4\3\2\33\35\5\b\5\2\34\33\3\2\2\2\34\35"+
		"\3\2\2\2\35\36\3\2\2\2\36 \5\6\4\2\37!\5\b\5\2 \37\3\2\2\2 !\3\2\2\2!"+
		"%\3\2\2\2\"$\5\6\4\2#\"\3\2\2\2$\'\3\2\2\2%#\3\2\2\2%&\3\2\2\2&)\3\2\2"+
		"\2\'%\3\2\2\2(*\5\b\5\2)(\3\2\2\2)*\3\2\2\2*+\3\2\2\2+,\7\2\2\3,\3\3\2"+
		"\2\2-.\7\3\2\2.\5\3\2\2\2/\61\5\n\6\2\60\62\5\b\5\2\61\60\3\2\2\2\61\62"+
		"\3\2\2\2\62\63\3\2\2\2\63\65\7\4\2\2\64\66\5\b\5\2\65\64\3\2\2\2\65\66"+
		"\3\2\2\2\668\3\2\2\2\679\5\f\7\28\67\3\2\2\289\3\2\2\29;\3\2\2\2:<\5\b"+
		"\5\2;:\3\2\2\2;<\3\2\2\2<=\3\2\2\2=>\7\5\2\2>\7\3\2\2\2?A\7\22\2\2@?\3"+
		"\2\2\2AB\3\2\2\2B@\3\2\2\2BC\3\2\2\2C\t\3\2\2\2DH\7\13\2\2EG\t\2\2\2F"+
		"E\3\2\2\2GJ\3\2\2\2HF\3\2\2\2HI\3\2\2\2I\13\3\2\2\2JH\3\2\2\2KQ\5\20\t"+
		"\2LM\5\16\b\2MN\5\20\t\2NP\3\2\2\2OL\3\2\2\2PS\3\2\2\2QO\3\2\2\2QR\3\2"+
		"\2\2R\r\3\2\2\2SQ\3\2\2\2TU\5\b\5\2U\17\3\2\2\2VY\5\6\4\2WY\5\22\n\2X"+
		"V\3\2\2\2XW\3\2\2\2Y\21\3\2\2\2Z^\5\26\f\2[^\5\30\r\2\\^\5\24\13\2]Z\3"+
		"\2\2\2][\3\2\2\2]\\\3\2\2\2^\23\3\2\2\2_a\7\6\2\2`b\5\b\5\2a`\3\2\2\2"+
		"ab\3\2\2\2bj\3\2\2\2ce\7\n\2\2df\5\b\5\2ed\3\2\2\2ef\3\2\2\2fg\3\2\2\2"+
		"gi\7\6\2\2hc\3\2\2\2il\3\2\2\2jh\3\2\2\2jk\3\2\2\2k\25\3\2\2\2lj\3\2\2"+
		"\2mo\t\3\2\2nm\3\2\2\2op\3\2\2\2pn\3\2\2\2pq\3\2\2\2qu\3\2\2\2rt\t\4\2"+
		"\2sr\3\2\2\2tw\3\2\2\2us\3\2\2\2uv\3\2\2\2v\27\3\2\2\2wu\3\2\2\2xz\7\20"+
		"\2\2yx\3\2\2\2yz\3\2\2\2z|\3\2\2\2{}\7\17\2\2|{\3\2\2\2}~\3\2\2\2~|\3"+
		"\2\2\2~\177\3\2\2\2\177\u0086\3\2\2\2\u0080\u0082\7\21\2\2\u0081\u0083"+
		"\7\17\2\2\u0082\u0081\3\2\2\2\u0083\u0084\3\2\2\2\u0084\u0082\3\2\2\2"+
		"\u0084\u0085\3\2\2\2\u0085\u0087\3\2\2\2\u0086\u0080\3\2\2\2\u0086\u0087"+
		"\3\2\2\2\u0087\31\3\2\2\2\30\34 %)\61\658;BHQX]aejpuy~\u0084\u0086";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}