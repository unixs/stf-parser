package net.unixcode.rts.parser.parsers.stf;

import net.unixcode.rts.parser.antlr.stf.stfParser;
import net.unixcode.rts.parser.api.IListenerStackFrame;
import net.unixcode.rts.parser.api.IParserListener;
import net.unixcode.rts.parser.api.IParserListenerContext;
import net.unixcode.rts.parser.api.stf.ISTF2XMLListenerCtxt;
import net.unixcode.rts.parser.parsers.CountableListenerStackFrame;
import org.jetbrains.annotations.NotNull;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;


import java.util.Deque;
import java.util.LinkedList;

@Component
@Scope("prototype")
public class STF2XMLListener extends StackableSTFListener<String, CountableListenerStackFrame<String>> implements IParserListener {
  protected class Frame extends CountableListenerStackFrame<String> {
    public Frame(CountableListenerStackFrame<String> prevFrame) {
      super(prevFrame);
    }

    public Frame() {
      super(null);
    }

    @Override
    public Frame createNext() {
      return new Frame(this);
    }

    @Override
    public String toString() {
      return getData() != null ? getData() : "";
    }
  }

  protected class Context implements ISTF2XMLListenerCtxt {
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

  IParserListenerContext listenerContext;

  // Stack<Frame> stack = new Stack<>();

  public STF2XMLListener() {
    this.listenerContext = new Context();
  }

  @Override
  public void enterStf(@NotNull stfParser.StfContext ctx) {
    // Добавляем в стек первый кадр с нулевой глубиной вложенности
    // В нём есть метод для определения этого факта
    pushNew();
  }

  @Override
  public void enterRootSections(@NotNull stfParser.RootSectionsContext ctx) {
    // Добавляем в стек кадр с единичной глубиной вложенности
    // Тут список секций
    // stack.peek().pushNew();
  }

  @Override
  public void enterList(@NotNull stfParser.ListContext ctx) {
    // Пихаем в стек счётчик секций в текущей секции
    // По факту потом будем считать так сколько было вообще обработано дочерних элементов
    // Когда будут обрабатываться все элементы, то надобность в нём может отпасть
    // TODO: Вообще то это может быть просто какой-то символ-ограничитель по которому мы можем нащупать гарницу
    // stack.push("0");
    pushNew().initCounter();
  }

  @Override
  public void enterSection (@NotNull stfParser.SectionContext ctx) {
    // Пихаем в стек пустой кадр для текущей секции
    pushNew().setData("");
  }


  @Override
  public void exitSection (@NotNull stfParser.SectionContext ctx) {
    // Мы должны знать сколько просмотрено секций
    // Чтобы выбросить их, обработать и положить на веришину одним кадром

    // Были ли дочерний список и секции в нём?
    var hasList = ctx.list() != null;

    // Коли список был, то нужно выкинуть верхний кадр
    // и прочитать из енго счётчик
    var counter = 0;
    if (hasList) {
      counter = peek().getCounter();
    }

    // Прям щас в стеке лежат или дочерние секции и пустой кадр или просто пустой кадр,
    // который был добавлен при входе в секцию
    // В этот пустой кадр по идее нужно писать содержимое этой текущей секции
    // из которой хотим выйти

    // Надобно всё дочернее выбросить, достать мой кадр, обернуть, в мой кадр и затолкнуть обратно

    // Тут задёшево храним кадры дочерних секций
    var tmpD = new LinkedList<>();

    // Выбрасываем всё что было в списке и что насчитано счётчиком
    for (int i = 0; i < counter; i++) {
      tmpD.add(pop());
    }

    // выбрасываем ненужный счётчик если список был
    // и счётчик значит тоже
    if (hasList) {
      pop();
    }

    // Ок, а теперь выбрасываем мой кадр
    var myFrame = pop();

    // Берём кадры из дека в обратном порядке
    // Присобачиваем к моему кадру дочернее барахлишко
    var reverseIterator = tmpD.descendingIterator();

    String data = myFrame.getData();
    while (reverseIterator.hasNext()) {
      data += reverseIterator.next();
    }

    // Эт названее для текущей секции
    var sectionName = ctx.sectionName().getText();

    // Заворачиваем дочерние в мой кадр
    if (data.isEmpty()) {
      myFrame.setData("<"+ sectionName +" />\n");
    }
    else {
      myFrame.setData("\n<"+ sectionName +">" + data + "</"+ sectionName +">\n");
    }

    // Кэп тоже доволен
    push(myFrame);
  }

  @Override
  public void exitListItem(@NotNull stfParser.ListItemContext ctx) {
    // Если дочерний элемент был секцией
    var hasSectionChild = ctx.section();

    if (hasSectionChild != null) {
      // инкрементим счётчик, который соответствует последнему списку
      // и лежит где-то ниже
      peek().count();
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

  CountableListenerStackFrame<String> pushNew() {
    return push(new Frame());
  }

  @Override
  public IParserListenerContext getContext() {
    return listenerContext;
  }
}
