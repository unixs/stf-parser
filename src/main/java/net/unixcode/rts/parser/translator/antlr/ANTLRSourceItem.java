package net.unixcode.rts.parser.translator.antlr;

import net.unixcode.rts.parser.api.compiler.CompilerType;
import net.unixcode.rts.parser.api.compiler.antlr.IANTLRSourceItem;
import net.unixcode.rts.parser.translator.DefaultSourceItem;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.text.MessageFormat;

public class ANTLRSourceItem extends DefaultSourceItem implements IANTLRSourceItem {
  final protected Logger log = LoggerFactory.getLogger(getClass());

  protected CharStream stream = null;

  public ANTLRSourceItem(String sourcePath) {
    super(sourcePath, CompilerType.STF);

    try {
      stream = CharStreams.fromFileName(this.sourcePath, StandardCharsets.UTF_16);
    }
    catch (IOException e) {
      log.error(MessageFormat.format("ERROR: Unable to compile STF file [{0}]", sourcePath));
      log.error(e.getMessage());
    }
  }

  @Override
  public CharStream getStream() {
    return stream;
  }

  @Contract("_ -> new")
  public static @NotNull IANTLRSourceItem stfSourceItemFactory(String srcPath) {
    return new ANTLRSourceItem(srcPath);
  }
}
