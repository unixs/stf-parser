package net.unixcode.rts.parser.translator.antlr.stf;

import net.unixcode.rts.parser.antlr.stf.stfLexer;
import net.unixcode.rts.parser.antlr.stf.stfParser;
import net.unixcode.rts.parser.api.IParserEmitter;
import net.unixcode.rts.parser.api.compiler.antlr.IANTLRParserListener;
import net.unixcode.rts.parser.api.compiler.CompilerType;
import net.unixcode.rts.parser.api.compiler.ISourceItem;
import net.unixcode.rts.parser.api.compiler.antlr.stf.ISTF2XMLListenerCtxt;
import net.unixcode.rts.parser.api.compiler.antlr.IANTLRSourceItem;
import net.unixcode.rts.parser.translator.antlr.ANTLRCompilerStrategy;
import net.unixcode.rts.parser.translator.xml.CabinXML2CXXTranslator;
import org.jetbrains.annotations.NotNull;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class STFCompilerStrategy extends ANTLRCompilerStrategy<stfLexer, stfParser> {
  final public static CompilerType COMPILER_TYPE = CompilerType.STF;

  protected ApplicationContext applicationContext;
  protected CabinXML2CXXTranslator translator;

  STFCompilerStrategy(ApplicationContext applicationContext, IParserEmitter emitter, STFExecutor stfParserExecutor, CabinXML2CXXTranslator translator) {
    super(stfParserExecutor, emitter);
    this.applicationContext = applicationContext;
    this.translator = translator;
  }

  @Override
  public void accept(@NotNull ISourceItem iSourceItem) {
    checkSourceItem(iSourceItem, COMPILER_TYPE);

    var sourceItem = (IANTLRSourceItem) iSourceItem;

    IANTLRParserListener listener = applicationContext.getBean(STF2XMLListener.class);

    var context = execute(sourceItem, listener);

    translator.accept((ISTF2XMLListenerCtxt) context);

    emitOutputData(listener);
  }
}
