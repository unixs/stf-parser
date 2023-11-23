package net.unixcode.rts.parser.compiler.antlr.stf;

import net.unixcode.rts.parser.antlr.stf.stfLexer;
import net.unixcode.rts.parser.antlr.stf.stfParser;
import net.unixcode.rts.parser.api.ICompilerFactory;
import net.unixcode.rts.parser.api.compiler.*;
import net.unixcode.rts.parser.api.compiler.antlr.stf.ISTF2XMLListenerFactory;
import net.unixcode.rts.parser.api.compiler.antlr.stf.ISTFSourceItem;
import net.unixcode.rts.parser.api.compiler.xml.XMLType;
import net.unixcode.rts.parser.compiler.antlr.ANTLRCompilerStrategy;
import net.unixcode.rts.parser.compiler.xml.XMLCompilerStrategy;
import net.unixcode.rts.parser.compiler.xml.XMLSourceItem;
import net.unixcode.rts.parser.services.FileEmitter;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;

@Component
public class STFCompilerStrategy extends ANTLRCompilerStrategy<stfLexer, stfParser> {
  final public static CompilerType COMPILER_TYPE = CompilerType.STF;
  final public static String FILE_EMITTER_EXTENSION = "xml";
  protected ICompilerFactory compilerFactory;
  protected ICompiler xmlCompiler;
  protected ISourceItemProvider sourceItemProvider;
  protected ISTF2XMLListenerFactory listenerFactory;

  STFCompilerStrategy(ISourceItemProvider sourceItemProvider, @NotNull ICompilerFactory compilerFactory, @NotNull XMLCompilerStrategy xmlCompilerStrategy, @NotNull FileEmitter emitter, STFExecutor stfParserExecutor, @NotNull ISTF2XMLListenerFactory listenerFactory) {
    super(stfParserExecutor, emitter.setExtension(FILE_EMITTER_EXTENSION));
    this.compilerFactory = compilerFactory;
    this.sourceItemProvider = sourceItemProvider;
    this.listenerFactory = listenerFactory;

    xmlCompiler = compilerFactory.get();
    xmlCompiler.setCompileStrategy(xmlCompilerStrategy);
  }


  @Override
  public void accept(@NotNull ISourceItem sourceItem) {
    var stfSourceItem = (STFSourceItem) sourceItem;

    checkSourceItem(stfSourceItem, COMPILER_TYPE);

    STF2XMLListener listener = listenerFactory.apply((ISTFSourceItem) sourceItem);

    var context = execute(stfSourceItem, listener);

    stfSourceItem.setContext(context);
  }

  @Override
  public void emit(ISourceItem sourceItem) {
    super.emit(sourceItem);

    compileXML((STFSourceItem) sourceItem);
  }

  protected void compileXML(@NotNull STFSourceItem stfSourceItem) {
    var outSourceItem = (XMLSourceItem) sourceItemProvider.apply(stfSourceItem.getOutPath());

    if (outSourceItem.getType() == XMLType.UNKNOWN) {
      log.info("XML transformation for UNKNOWN XML item has been skipped");
      return;
    }

    xmlCompiler.accept(outSourceItem);
    xmlCompiler.emit(outSourceItem);
  }
}
