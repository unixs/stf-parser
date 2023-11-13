package net.unixcode.rts.parser.compiler.antlr.stf;

import net.unixcode.rts.parser.antlr.stf.stfLexer;
import net.unixcode.rts.parser.antlr.stf.stfParser;
import net.unixcode.rts.parser.api.ICompilerFactory;
import net.unixcode.rts.parser.api.compiler.*;
import net.unixcode.rts.parser.api.compiler.antlr.IANTLRParserListener;
import net.unixcode.rts.parser.api.compiler.antlr.IANTLRSourceItem;
import net.unixcode.rts.parser.compiler.antlr.ANTLRCompilerStrategy;
import net.unixcode.rts.parser.compiler.xml.XMLCompilerStrategy;
import net.unixcode.rts.parser.services.FileEmitter;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Lookup;
import org.springframework.stereotype.Component;

@Component
public class STFCompilerStrategy extends ANTLRCompilerStrategy<stfLexer, stfParser> {
  final public static CompilerType COMPILER_TYPE = CompilerType.STF;
  final public static String FILE_EMITTER_EXTENSION = "xml";
  protected ICompilerFactory compilerFactory;
  protected ICompiler xmlCompiler;
  protected ISourceItemProvider sourceItemProvider;

  STFCompilerStrategy(ISourceItemProvider sourceItemProvider, @NotNull ICompilerFactory compilerFactory, @NotNull XMLCompilerStrategy xmlCompilerStrategy, @NotNull FileEmitter emitter, STFExecutor stfParserExecutor) {
    super(stfParserExecutor, emitter.setExtension(FILE_EMITTER_EXTENSION));
    this.compilerFactory = compilerFactory;
    this.sourceItemProvider = sourceItemProvider;

    xmlCompiler = compilerFactory.get();
    xmlCompiler.setCompileStrategy(xmlCompilerStrategy);
  }

  @Override
  public void accept(@NotNull ISourceItem sourceItem) {
    checkSourceItem(sourceItem, COMPILER_TYPE);

    var antlrSourceItem = (IANTLRSourceItem) sourceItem;

    IANTLRParserListener listener = lookupListener();

    var context = execute(antlrSourceItem, listener);

    antlrSourceItem.setContext(context);
  }

  @Override
  public void emit(ISourceItem sourceItem) {
    super.emit(sourceItem);

    compileXML(sourceItem);
  }

  protected void compileXML(@NotNull ISourceItem antlrSourceItem) {
    var outSourceItem = this.sourceItemProvider.apply(antlrSourceItem.getOutPath());

    xmlCompiler.accept(outSourceItem);
    xmlCompiler.emit(outSourceItem);
  }

  @Lookup
  protected STF2XMLListener lookupListener() {
    return null;
  }
}
