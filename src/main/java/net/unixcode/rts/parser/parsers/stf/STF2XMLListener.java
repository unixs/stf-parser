package net.unixcode.rts.parser.parsers.stf;

import net.unixcode.rts.parser.antlr.stf.stfParser;
import net.unixcode.rts.parser.api.IParserListener;
import net.unixcode.rts.parser.api.IParserListenerContext;
import net.unixcode.rts.parser.api.stf.ISTF2XMLListenerCtxt;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import net.unixcode.rts.parser.antlr.stf.stfBaseListener;

@Component
@Scope("prototype")
public class STF2XMLListener extends stfBaseListener implements IParserListener {
  IParserListenerContext listenerContext;

  @Autowired
  public STF2XMLListener(ISTF2XMLListenerCtxt listenerContext) {
    this.listenerContext = listenerContext;
  }

  @Override
  public void enterSection (@NotNull stfParser.SectionContext ctx) {
    // System.out.println(ctx.nodeName());
  }

  @Override
  public void exitSection (@NotNull stfParser.SectionContext ctx) {
    System.out.println("\t" + ctx.nodeName().getText());
  }

  @Override
  public IParserListenerContext getContext() {
    return listenerContext;
  }
}
