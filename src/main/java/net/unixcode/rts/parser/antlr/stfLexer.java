// Generated from stf.g4 by ANTLR 4.3

  package net.unixcode.rts.parser.antlr;

import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class stfLexer extends Lexer {
	static { RuntimeMetaData.checkVersion("4.3", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		MAGIC_HEADING_1=1, LEFT_PAREN=2, RIGHT_PAREN=3, STRING=4, SYMBOL=5, LEFT_BRACKET=6, 
		RIGHT_BRACKET=7, PLUS=8, ANY_LETTER=9, CAPITAL_LETTER=10, LETTER=11, CYR_LETTER=12, 
		DIGIT=13, DASH=14, DOT=15, WS=16, NEWLINE=17;
	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	public static final String[] tokenNames = {
		"'\\u0000'", "'\\u0001'", "'\\u0002'", "'\\u0003'", "'\\u0004'", "'\\u0005'", 
		"'\\u0006'", "'\\u0007'", "'\b'", "'\t'", "'\n'", "'\\u000B'", "'\f'", 
		"'\r'", "'\\u000E'", "'\\u000F'", "'\\u0010'", "'\\u0011'"
	};
	public static final String[] ruleNames = {
		"MAGIC_HEADING_1", "LEFT_PAREN", "RIGHT_PAREN", "STRING", "SYMBOL", "LEFT_BRACKET", 
		"RIGHT_BRACKET", "PLUS", "ANY_LETTER", "CAPITAL_LETTER", "LETTER", "CYR_LETTER", 
		"DIGIT", "DASH", "DOT", "WS", "NEWLINE"
	};


	public stfLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "stf.g4"; }

	@Override
	public String[] getTokenNames() { return tokenNames; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public String[] getModeNames() { return modeNames; }

	@Override
	public ATN getATN() { return _ATN; }

	public static final String _serializedATN =
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\2\23r\b\1\4\2\t\2\4"+
		"\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t"+
		"\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3"+
		"\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\3\3\3\3\4\3\4"+
		"\3\5\3\5\7\5K\n\5\f\5\16\5N\13\5\3\5\3\5\3\6\3\6\3\6\5\6U\n\6\3\7\3\7"+
		"\3\b\3\b\3\t\3\t\3\n\3\n\5\n_\n\n\3\13\3\13\3\f\3\f\3\r\3\r\3\16\3\16"+
		"\3\17\3\17\3\20\3\20\3\21\3\21\3\22\3\22\3\22\3\22\3L\2\23\3\3\5\4\7\5"+
		"\t\6\13\7\r\b\17\t\21\n\23\13\25\f\27\r\31\16\33\17\35\20\37\21!\22#\23"+
		"\3\2\t\b\2&&((..<<AB~~\3\2C\\\5\2aac|\u0432\u0451\5\2\u0403\u0403\u0412"+
		"\u0451\u0453\u0453\3\2\62;\4\2\13\13\"\"\4\2\f\f\17\17u\2\3\3\2\2\2\2"+
		"\5\3\2\2\2\2\7\3\2\2\2\2\t\3\2\2\2\2\13\3\2\2\2\2\r\3\2\2\2\2\17\3\2\2"+
		"\2\2\21\3\2\2\2\2\23\3\2\2\2\2\25\3\2\2\2\2\27\3\2\2\2\2\31\3\2\2\2\2"+
		"\33\3\2\2\2\2\35\3\2\2\2\2\37\3\2\2\2\2!\3\2\2\2\2#\3\2\2\2\3%\3\2\2\2"+
		"\5D\3\2\2\2\7F\3\2\2\2\tH\3\2\2\2\13T\3\2\2\2\rV\3\2\2\2\17X\3\2\2\2\21"+
		"Z\3\2\2\2\23^\3\2\2\2\25`\3\2\2\2\27b\3\2\2\2\31d\3\2\2\2\33f\3\2\2\2"+
		"\35h\3\2\2\2\37j\3\2\2\2!l\3\2\2\2#n\3\2\2\2%&\7U\2\2&\'\7K\2\2\'(\7O"+
		"\2\2()\7K\2\2)*\7U\2\2*+\7C\2\2+,\7B\2\2,-\7B\2\2-.\7B\2\2./\7B\2\2/\60"+
		"\7B\2\2\60\61\7B\2\2\61\62\7B\2\2\62\63\7B\2\2\63\64\7B\2\2\64\65\7B\2"+
		"\2\65\66\7L\2\2\66\67\7K\2\2\678\7P\2\289\7Z\2\29:\7\62\2\2:;\7v\2\2;"+
		"<\7\63\2\2<=\7v\2\2=>\7a\2\2>?\7a\2\2?@\7a\2\2@A\7a\2\2AB\7a\2\2BC\7a"+
		"\2\2C\4\3\2\2\2DE\7*\2\2E\6\3\2\2\2FG\7+\2\2G\b\3\2\2\2HL\7$\2\2IK\13"+
		"\2\2\2JI\3\2\2\2KN\3\2\2\2LM\3\2\2\2LJ\3\2\2\2MO\3\2\2\2NL\3\2\2\2OP\7"+
		"$\2\2P\n\3\2\2\2QU\t\2\2\2RU\5\r\7\2SU\5\17\b\2TQ\3\2\2\2TR\3\2\2\2TS"+
		"\3\2\2\2U\f\3\2\2\2VW\7]\2\2W\16\3\2\2\2XY\7_\2\2Y\20\3\2\2\2Z[\7-\2\2"+
		"[\22\3\2\2\2\\_\5\27\f\2]_\5\25\13\2^\\\3\2\2\2^]\3\2\2\2_\24\3\2\2\2"+
		"`a\t\3\2\2a\26\3\2\2\2bc\t\4\2\2c\30\3\2\2\2de\t\5\2\2e\32\3\2\2\2fg\t"+
		"\6\2\2g\34\3\2\2\2hi\7/\2\2i\36\3\2\2\2jk\7\60\2\2k \3\2\2\2lm\t\7\2\2"+
		"m\"\3\2\2\2no\t\b\2\2op\3\2\2\2pq\b\22\2\2q$\3\2\2\2\6\2LT^\3\b\2\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}