package net.unixcode.rts.parser.compiler.antlr.stf;

import org.antlr.v4.runtime.ConsoleErrorListener;
import org.antlr.v4.runtime.RecognitionException;
import org.antlr.v4.runtime.Recognizer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class STFLexerErrorListener extends ConsoleErrorListener {
  final protected Logger log = LoggerFactory.getLogger(getClass());

  @Override
  public void syntaxError(Recognizer<?, ?> recognizer, Object offendingSymbol, int line, int charPositionInLine, String msg, RecognitionException e) {
    String err = "line " + line + ":" + charPositionInLine + " " + msg;

    log.error(err);
    log.info("Try to check source file encoding. It must be UTF-16LE.");

    System.exit(1);
  }

  public static class LexerError extends Error {
    public LexerError(String msg) {
      super(msg);
    }
  }
}
