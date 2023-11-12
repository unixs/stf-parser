package net.unixcode.rts.parser.compiler.antlr.stf;

import net.unixcode.rts.parser.antlr.stf.stfLexer;
import net.unixcode.rts.parser.antlr.stf.stfParser;
import net.unixcode.rts.parser.api.compiler.antlr.IANTLRParserListener;
import net.unixcode.rts.parser.api.compiler.CompilerType;
import net.unixcode.rts.parser.api.compiler.ISourceItem;
import net.unixcode.rts.parser.api.compiler.antlr.stf.ISTF2XMLListenerCtxt;
import net.unixcode.rts.parser.api.compiler.antlr.IANTLRSourceItem;
import net.unixcode.rts.parser.compiler.antlr.ANTLRCompilerStrategy;
import net.unixcode.rts.parser.compiler.xml.CabinXML2CXXTranslator;
import net.unixcode.rts.parser.services.FileEmitter;
import org.jetbrains.annotations.NotNull;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class STFCompilerStrategy extends ANTLRCompilerStrategy<stfLexer, stfParser> {
  final public static CompilerType COMPILER_TYPE = CompilerType.STF;
  final public static String FILE_EMITTER_EXTENSION = "xml";

  protected ApplicationContext applicationContext;
  protected CabinXML2CXXTranslator translator;

  STFCompilerStrategy(ApplicationContext applicationContext, FileEmitter emitter, STFExecutor stfParserExecutor, CabinXML2CXXTranslator translator) {
    super(stfParserExecutor, emitter.setExtension(FILE_EMITTER_EXTENSION));
    this.applicationContext = applicationContext;
    this.translator = translator;
  }

  @Override
  public void accept(@NotNull ISourceItem sourceItem) {
    checkSourceItem(sourceItem, COMPILER_TYPE);

    var antlrSourceItem = (IANTLRSourceItem) sourceItem;

    IANTLRParserListener listener = applicationContext.getBean(STF2XMLListener.class);

    var context = execute(antlrSourceItem, listener);

    translator.accept((ISTF2XMLListenerCtxt) context);

    antlrSourceItem.setContext(context);
  }
}
