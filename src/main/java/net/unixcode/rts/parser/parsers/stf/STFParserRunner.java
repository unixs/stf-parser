package net.unixcode.rts.parser.parsers.stf;

import net.unixcode.rts.parser.api.IInputStreamsProvider;
import net.unixcode.rts.parser.parsers.stf.STF2XMLListener;
import net.unixcode.rts.parser.parsers.stf.STFParserExecutor;
import net.unixcode.rts.parser.services.DefaultParserRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import net.unixcode.rts.parser.antlr.stf.stfLexer;
import net.unixcode.rts.parser.antlr.stf.stfParser;

@Service("stf_parser_runner")
public class STFParserRunner extends DefaultParserRunner<stfLexer, stfParser> {
  @Autowired
  public STFParserRunner(IInputStreamsProvider streamsProvider, STFParserExecutor stfParserExecutor, STF2XMLListener listener) {
    super(streamsProvider, stfParserExecutor, listener);
  }
}
