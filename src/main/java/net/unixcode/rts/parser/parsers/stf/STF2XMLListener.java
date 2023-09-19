package net.unixcode.rts.parser.parsers.stf;

import net.unixcode.rts.parser.antlr.stf.stfParser;
import net.unixcode.rts.parser.api.IParserListener;
import net.unixcode.rts.parser.api.IParserListenerContext;
import net.unixcode.rts.parser.api.stf.ISTF2XMLListenerCtxt;
import org.jetbrains.annotations.NotNull;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import net.unixcode.rts.parser.antlr.stf.stfBaseListener;

@Component
@Scope("prototype")
public class STF2XMLListener extends stfBaseListener implements IParserListener {
  IParserListenerContext listenerContext;

  public STF2XMLListener() {
    this.listenerContext = new Context();
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

  public class Context implements ISTF2XMLListenerCtxt {
    final public static String EXTENSION = "xml";
    protected String sourcePath;
    protected IParserListener listener;
    protected boolean processed = false;

    public Context() {
      System.out.println("New listener ctxt.");
    }

    @Override
    public IParserListenerContext setSourcePath(String path) {
      sourcePath = path;

      return this;
    }

    @Override
    public String getSourcePath() {
      return sourcePath;
    }

    @Override
    public String getExtensiion() {
      return EXTENSION;
    }

    @Override
    public boolean processed() {
      return processed;
    }

    @Override
    public void setProcessed() {
      processed = true;
    }
  }
}
