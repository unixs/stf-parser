package net.unixcode.rts.parser.parsers.stf;

import net.unixcode.rts.parser.api.IIterableStreamsProvider;
import net.unixcode.rts.parser.api.IParserEmitter;
import net.unixcode.rts.parser.api.IParserRunner;
import net.unixcode.rts.parser.parsers.BaseRunnerProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import net.unixcode.rts.parser.antlr.stf.stfLexer;
import net.unixcode.rts.parser.antlr.stf.stfParser;

@Service
public class STFRunnerProvider extends BaseRunnerProvider<stfLexer, stfParser> {

  @Autowired
  public STFRunnerProvider(ApplicationContext applicationContext, IIterableStreamsProvider streamsProvider, STFExecutor stfParserExecutor, IParserEmitter emitter) {
    super(applicationContext, streamsProvider, stfParserExecutor, emitter);
  }

  @Override
  public IParserRunner get() {
    return this::run;
  }
}
