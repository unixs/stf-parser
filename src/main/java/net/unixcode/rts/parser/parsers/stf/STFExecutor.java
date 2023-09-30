package net.unixcode.rts.parser.parsers.stf;

import net.unixcode.rts.parser.antlr.stf.stfLexer;
import net.unixcode.rts.parser.antlr.stf.stfParser;
import net.unixcode.rts.parser.api.IParserListener;
import net.unixcode.rts.parser.api.IParserListenerContext;
import net.unixcode.rts.parser.api.stf.ISTFLexerSupplier;
import net.unixcode.rts.parser.api.stf.ISTFParserSupplier;
import net.unixcode.rts.parser.parsers.BaseExecutor;
import org.antlr.v4.runtime.ANTLRErrorListener;
import org.antlr.v4.runtime.RecognitionException;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class STFExecutor extends BaseExecutor<stfLexer, stfParser> {
  final protected Logger log = LoggerFactory.getLogger(getClass());

  @Autowired
  STFExecutor(ISTFLexerSupplier lexer, ISTFParserSupplier parser, ANTLRErrorListener errorListener) {
    super(lexer, parser, errorListener);
  }

  @Override
  public IParserListenerContext exec(@NotNull stfParser parser, IParserListener listener) {
    parser.addParseListener(listener);

    try {
      parser.removeErrorListeners();
      parser.stf();
    }
    catch (RecognitionException e) {
      log.error("ANTLR error");
      log.error(e.getMessage());

      System.exit(1);
    }


    return listener.getContext();
  }
}
