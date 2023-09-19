package net.unixcode.rts.parser.parsers.stf;

import net.unixcode.rts.parser.antlr.stf.stfParser;
import net.unixcode.rts.parser.api.IParserListener;
import net.unixcode.rts.parser.api.IParserListenerContext;
import net.unixcode.rts.parser.api.stf.ISTF2XMLListenerCtxt;
import org.jetbrains.annotations.NotNull;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import net.unixcode.rts.parser.antlr.stf.stfBaseListener;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

@Component
@Scope("prototype")
public class STF2XMLListener extends stfBaseListener implements IParserListener {
  IParserListenerContext listenerContext;

  Stack<String> stack = new Stack<>();

  public STF2XMLListener() {
    this.listenerContext = new Context();
  }

  @Override
  public void enterSection (@NotNull stfParser.SectionContext ctx) {
    // Пихаем в стек пустой кадр для текущей секции
    stack.push("");
  }

  @Override
  public void exitSection (@NotNull stfParser.SectionContext ctx) {
    // Мы должны знать сколько просмотрено секций
    // Чтобы выбросить их, обработать и положить на веришину одним кадром

    // Были ли дочерний список и секции в нём?
    var hasList = ctx.nodeList() != null;

    // Коли список был, то на вершине стека счётчик дочерних секций и, пади,
    // в стеке под счётчиком кадры этих секций
    var counter = 0;
    if (hasList) {
      counter = Integer.parseInt(stack.pop());
    }

    // Прям щас в стеке лежат или дочернии секции и пустой кадр или просто пустой кадр,
    // который был добавлен при входе в секцию
    // В этот пустой кадр по идее нужно писать содержимое этой текущей секции
    // из которой хотим выйти

    // Надобно всё дочернее выбросить, достать мой кадр, обернуть, в мой кадр и затолкнуть обратно

    // Тут задёшево храним кадры дочерних секций
    Deque<String> tmpD = new LinkedList<>();

    for (int i = 0; i < counter; i++) {
      tmpD.add(stack.pop());
    }

    // Ок, а теперь выбрасываем мой кадр
    var myFrame = stack.pop();

    // Берём кадры из дека в обратном порядке
    // Присобачиваем к моему кадру дочернее барахлишко
    var reverseIterator = tmpD.descendingIterator();

    while (reverseIterator.hasNext()) {
      myFrame += reverseIterator.next();
    }

    // Эт названее для текущей секции
    var nodeName = ctx.nodeName().getText();

    // Заворачиваем дочерние в мой кадр
    myFrame = "<"+ nodeName +">" + myFrame + "</"+ nodeName +">\n";

    // Кэп тоже доволен
    stack.push(myFrame);
  }

  @Override
  public void enterNodeList(@NotNull stfParser.NodeListContext ctx) {
    // Пихаем в стек счётчик секций в текущей секции
    // По факту потом будем считать так сколько было вообще обработано дочерних элементов
    // Когда будут обрабатываться все элементы, то надобность в нём может отпасть
    // TODO: Вообще то это может быть просто какой-то символ-ограничитель по которому мы можем нащупать гарницу
    stack.push("0");
  }

  @Override
  public void exitNodeListItem(@NotNull stfParser.NodeListItemContext ctx) {
    // Если дочерний элемент был секцией
    var hasSectionChild = ctx.section();

    if (hasSectionChild != null) {
      // выбросим его кадр и число под ним, которое добавил nodeList
      var sectionFrame = stack.pop();
      var counter = Integer.parseInt(stack.pop());

      // инкрементим счётчик
      counter++;

      // И возвращаем наоборот чтобы счётчик наверху
      stack.push(sectionFrame);
      stack.push(String.valueOf(counter));
    }
  }

  @Override
  public void exitStf(@NotNull stfParser.StfContext ctx) {
    String out = "<eng>\n";

    for (var frame : stack) {
      out += frame + "\n";
    }

    out += "\n</eng>";

    System.out.println("Parser DONE.");
    System.out.println(out);
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
