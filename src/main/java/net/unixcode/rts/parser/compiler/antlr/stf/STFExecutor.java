package net.unixcode.rts.parser.compiler.antlr.stf;

import net.unixcode.rts.parser.antlr.stf.stfLexer;
import net.unixcode.rts.parser.antlr.stf.stfParser;
import net.unixcode.rts.parser.api.compiler.antlr.IANTLRParserListener;
import net.unixcode.rts.parser.api.compiler.antlr.IANTLRListenerContext;
import net.unixcode.rts.parser.api.compiler.antlr.stf.ISTFLexerSupplier;
import net.unixcode.rts.parser.api.compiler.antlr.stf.ISTFParserSupplier;
import net.unixcode.rts.parser.compiler.antlr.BaseANTLRExecutor;
import org.antlr.v4.runtime.ANTLRErrorListener;
import org.antlr.v4.runtime.RecognitionException;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component
public class STFExecutor extends BaseANTLRExecutor<stfLexer, stfParser> {
  final protected Logger log = LoggerFactory.getLogger(getClass());

  @Autowired
  STFExecutor(ISTFLexerSupplier lexer, ISTFParserSupplier parser, ANTLRErrorListener errorListener) {
    super(lexer, parser, errorListener);
  }

  @Override
  public IANTLRListenerContext exec(@NotNull stfParser parser, IANTLRParserListener listener) {
    parser.addParseListener(listener);

    try {
      parser.removeErrorListeners();
      parser.stf();
      listener.getContext().setProcessed();
    }
    catch (RecognitionException e) {
      log.error("Parse error");
      log.error(e.getMessage());

      System.exit(1);
    }


    return listener.getContext();
  }
}
