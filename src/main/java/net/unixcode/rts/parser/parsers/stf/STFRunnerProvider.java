package net.unixcode.rts.parser.parsers.stf;

import net.unixcode.rts.parser.api.IIterableStreamsProvider;
import net.unixcode.rts.parser.api.IParserRunner;
import net.unixcode.rts.parser.parsers.BaseRunnerProvider;
import org.antlr.v4.runtime.tree.ParseTreeListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import net.unixcode.rts.parser.antlr.stf.stfLexer;
import net.unixcode.rts.parser.antlr.stf.stfParser;

@Service("stf_parser_runner_provider")
public class STFRunnerProvider extends BaseRunnerProvider<stfLexer, stfParser> {
  @Autowired
  public STFRunnerProvider(ApplicationContext applicationContext, IIterableStreamsProvider streamsProvider, STFExecutor stfParserExecutor,
                           STF2XMLListener listener) {
    super(applicationContext, streamsProvider, stfParserExecutor, listener);
  }

  @Override
  public IParserRunner get() {
    return () -> {
      ParseTreeListener listener = applicationContext.getBean(STF2XMLListener.class);

      this.run(listener);
    };
  }
}
