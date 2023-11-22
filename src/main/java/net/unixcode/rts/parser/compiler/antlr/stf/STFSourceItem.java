package net.unixcode.rts.parser.compiler.antlr.stf;

import net.unixcode.rts.parser.api.compiler.ISourceItem;
import net.unixcode.rts.parser.api.compiler.antlr.stf.ISTFSourceItem;
import net.unixcode.rts.parser.api.compiler.antlr.stf.ISTFTypeProvider;
import net.unixcode.rts.parser.api.compiler.antlr.stf.STFType;
import net.unixcode.rts.parser.compiler.antlr.ANTLRSourceItem;
import org.jetbrains.annotations.NotNull;

public class STFSourceItem extends ANTLRSourceItem implements ISTFSourceItem {
  protected STFType stfType = null;

  public STFSourceItem(@NotNull ISTFTypeProvider typeProvider, String sourcePath) {
    super(sourcePath);
    this.stfType = typeProvider.apply(this);
  }

  public static @NotNull ISourceItem factory(ISTFTypeProvider typeProvider, String src) {
    return new STFSourceItem(typeProvider, src);
  }

  @Override
  public STFType getType() {
    return stfType;
  }
}
