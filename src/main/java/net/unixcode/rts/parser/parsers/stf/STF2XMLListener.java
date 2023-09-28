package net.unixcode.rts.parser.parsers.stf;

import net.unixcode.rts.parser.antlr.stf.stfParser;
import net.unixcode.rts.parser.api.IParserListener;
import net.unixcode.rts.parser.api.IParserListenerContext;
import net.unixcode.rts.parser.api.stf.ISTF2XMLListenerCtxt;
import net.unixcode.rts.parser.parsers.CountableListenerStackFrame;
import org.jetbrains.annotations.NotNull;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.w3c.dom.*;

import java.util.LinkedList;

@Component
@Scope("prototype")
public class STF2XMLListener extends StackableSTFListener<Node, CountableListenerStackFrame<Node>> implements IParserListener {
  protected static class Frame extends CountableListenerStackFrame<Node> {
    public Frame(CountableListenerStackFrame<Node> prevFrame) {
      super(prevFrame);
    }

    public Frame() {
      super(null);
    }

    @Override
    public Frame createNext() {
      return new Frame(this);
    }
  }

  protected ISTF2XMLListenerCtxt listenerContext;

  public STF2XMLListener(ISTF2XMLListenerCtxt listenerContext) {
    this.listenerContext = listenerContext;
  }

  protected Document doc() {
    return listenerContext.getDoc();
  }

  protected Element newElement(String name) {
    return doc().createElement(name);
  }

  protected Attr newAttr(String name, String value) {
    var attr = doc().createAttribute(name);
    attr.setValue(value);

    return attr;
  }

  protected Text newText(String text) {
    return doc().createTextNode(text);
  }

  protected Frame pushElement(String name) {
    return pushData(newElement(name));
  }

  protected Frame pushAttr(String name, String value) {
    return pushData(newAttr(name, value));
  }

  protected Frame pushText(String text) {
    return pushData(newText(text));
  }

  protected CountableListenerStackFrame<Node> pushNew() {
    return push(new Frame());
  }

  protected Frame pushData(Node data) {
    return (Frame) pushNew().setData(data);
  }

  protected Node popData() {
    return pop().getData();
  }

  protected Frame pushCounter() {
    return (Frame) pushNew().initCounter();
  }

  protected void count() {
    peek().count();
  }

  @Override
  public IParserListenerContext getContext() {
    return listenerContext;
  }

  @Override
  public void enterList(@NotNull stfParser.ListContext ctx) {
    // Пихаем в стек счётчик текущей секции
    // Будем считать сколько было обработано дочерних элементов
    // чтобы потом их выбросить и завернуть в секцию
    pushCounter();
  }

  @Override
  public void enterSection (@NotNull stfParser.SectionContext ctx) {
    // Понятия не имеем как называется секция
    // Ничего не делаем. Как будем выходить, так и разберёмся
  }

  @Override
  public void exitSection (@NotNull stfParser.SectionContext ctx) {
    // Мы должны знать сколько просмотрено секций
    // Чтобы выбросить их, обработать и положить на веришину одним кадром
    var elementName = ctx.sectionName().getText();

    // Были ли дочерний список и секции в нём?
    var hasList = ctx.list() != null;

    // Коли список был,то нужно прочитать из него счётчик
    var counter = 0;
    if (hasList) {
      counter = peek().getCounter();
    }

    // Прям щас в стеке лежат дочерние элементы и счётчик,
    // который был добавлен при входе в секцию

    // Надобно всё дочернее выбросить, обернуть в мой новый кадр и затолкнуть обратно

    // Тут задёшево храним кадры дочерних элементов
    var childs = new LinkedList<Node>();

    // Выбрасываем всё что было в списке и что насчитано счётчиком
    for (int i = 0; i < counter; i++) {
      childs.add(popData());
    }

    // выбрасываем ненужный счётчик если список был
    // и счётчик значит тоже
    if (hasList) {
      pop();
    }

    // Ок, а теперь узел текущей секции
    var section = newElement(elementName);

    section.setAttribute("type", "section");

    // Добавляем в секцию все веброшенные узлы
    var reverseIterator = childs.descendingIterator();
    while (reverseIterator.hasNext()) {
      section.appendChild(reverseIterator.next());
    }

    pushData(section);
  }

  @Override public void exitWord(@NotNull stfParser.WordContext ctx) {
    var node = newElement("word");
      node.setTextContent(ctx.getText());

    pushData(node);
  }

  @Override
  public void exitString(@NotNull stfParser.StringContext ctx) {
    var node = newElement("string");
    var str = ctx.getText();

    node.setTextContent(str.substring(1, str.length() - 1).trim());

    pushData(node);
  }

  @Override
  public void exitNumber(@NotNull stfParser.NumberContext ctx) {
    var node = newElement("number");
      node.appendChild(popData());
      node.setAttributeNode((Attr) popData());

    pushData(node);
  }

  @Override
  public void exitIntNumber(@NotNull stfParser.IntNumberContext ctx) {
    pushAttr("type", "int");
    pushText(ctx.getText());
  }

  @Override
  public void exitFloatNumber(@NotNull stfParser.FloatNumberContext ctx) {
    pushAttr("type", "float");
    pushText(ctx.getText());
  }

  @Override
  public void exitListItem(@NotNull stfParser.ListItemContext ctx) {
    // Если дочерний элемент был - инкрементим ближайший в стеке счётчик
    // Потом при выходе из секции сможем выбросить нужное кол-во
    // обработанных элементов списка
    count();
  }

  @Override
  public void exitHeading(@NotNull stfParser.HeadingContext ctx) {
    var heading = newElement("header");
      heading.setTextContent(ctx.getText());

    pushData(heading);
  }

  @Override
  public void exitStf(@NotNull stfParser.StfContext ctx) {
    var root = newElement("root");

    for (var node : stack) {
      root.appendChild(node.getData());
    }

    doc().appendChild(root);
  }
}
