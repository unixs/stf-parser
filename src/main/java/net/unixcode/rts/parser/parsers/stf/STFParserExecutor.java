package net.unixcode.rts.parser.parsers.stf;

import net.unixcode.rts.parser.api.BaseParserExecutor;
import net.unixcode.rts.parser.antlr.stf.stfLexer;
import net.unixcode.rts.parser.antlr.stf.stfParser;
import net.unixcode.rts.parser.api.stf.ISTFLexerSupplier;
import net.unixcode.rts.parser.api.stf.ISTFParserSupplier;
import org.antlr.v4.runtime.tree.ParseTreeListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class STFParserExecutor extends BaseParserExecutor<stfLexer, stfParser> {
  @Autowired
  STFParserExecutor(ISTFLexerSupplier lexer, ISTFParserSupplier parser) {
    super(lexer, parser);
  }

  @Override
  public String exec(ParseTreeListener listener) {
    parser.addParseListener(listener);
    parser.stf();

    return listener.toString();
  }
}
