grammar stf;

@header {
  package net.unixcode.rts.parser.antlr.stf;
}

stf:
  heading
  WS
  rootBlocks?
  WS?
  EOF;

heading:
  MAGIC_HEADING;

block:
  section | comment;

rootBlocks:
  block (WS? block)*;

section:
  sectionName
  WS?
  body;

body:
  LEFT_PAREN
  WS?
  list?
  WS?
  RIGHT_PAREN;

sectionName:
  ANY_LETTERS (ANY_LETTERS | DIGITS)*;

list:
  listItem (WS listItem)*;

listItem:
  block | term;

term:
  number | string | point | word;

comment:
  SKIP_KW
  WS?
  body;

string:
  STRING  ( WS? PLUS WS? STRING )*;

point:
  number WS? COMMA WS? number;

word:
  (ANY_LETTERS | CYR_LETTERS | SYMBOL | DIGITS | COMMA | DOT | DASH | PLUS)+;

number:
  intNumber | floatNumber;

intNumber:
  DASH? DIGITS;

floatNumber:
  DASH? DIGITS (DOT DIGITS);

MAGIC_HEADING:
  'SIM' .*? '______';

LEFT_PAREN:
  '(';

RIGHT_PAREN:
  ')';

QUOTE:
  '"';

STRING:
  QUOTE ANY QUOTE;

SKIP_KW:
  [Ss]'kip';

SYMBOL:
  [&:?№*~`§±|;!#%=\\/@$] | LEFT_BRACKET | RIGHT_BRACKET;

COMMA:
  ',';

LEFT_BRACKET:
  '[';

RIGHT_BRACKET:
  ']';

PLUS:
  '+';

ANY_LETTERS:
  (LETTER | CAPITAL_LETTER)+;

CAPITAL_LETTER:
  [A-Z];

LETTER:
  [a-z_а-я];

CYR_LETTERS:
  [а-яА-ЯёЁ]+;

DIGITS:
  [0-9]+;

DASH:
  '-';

DOT:
  '.';

WS:
  [ \t\r\n\uFFFE\uFEFF]+;

fragment ANY:
  .*?;
