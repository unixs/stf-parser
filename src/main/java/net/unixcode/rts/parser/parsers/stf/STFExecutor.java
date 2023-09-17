package net.unixcode.rts.parser.parsers.stf;

import net.unixcode.rts.parser.antlr.stf.stfLexer;
import net.unixcode.rts.parser.antlr.stf.stfParser;
import net.unixcode.rts.parser.api.stf.ISTFLexerSupplier;
import net.unixcode.rts.parser.api.stf.ISTFParserSupplier;
import net.unixcode.rts.parser.parsers.BaseExecutor;
import org.antlr.v4.runtime.tree.ParseTreeListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class STFExecutor extends BaseExecutor<stfLexer, stfParser> {
  @Autowired
  STFExecutor(ISTFLexerSupplier lexer, ISTFParserSupplier parser) {
    super(lexer, parser);
  }

  @Override
  public void exec(stfParser parser, ParseTreeListener listener) {
    parser.addParseListener(listener);
    parser.stf();
  }
}
