package net.unixcode.rts.parser.compiler.antlr.stf;

import net.unixcode.rts.parser.api.compiler.antlr.stf.ISTF2XMLListenerCtxt;
import net.unixcode.rts.parser.api.compiler.antlr.stf.ISTF2XMLListenerFactory;
import net.unixcode.rts.parser.api.compiler.antlr.stf.ISTFSourceItem;
import org.springframework.beans.factory.annotation.Lookup;
import org.springframework.stereotype.Component;

@Component
public class STF2XMLListenerFactory implements ISTF2XMLListenerFactory {
  @Override
  public STF2XMLListener apply(ISTFSourceItem sourceItem) {
    var context = lookupContext();

    context.setSourceItem(sourceItem);

    return new STF2XMLListener(context);
  }

  @Lookup
  protected ISTF2XMLListenerCtxt lookupContext() {
    return null;
  }
}
