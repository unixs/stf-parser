grammar stf;

@header {
  package net.unixcode.rts.parser.antlr.stf;
}

stf:
  heading
  WS?
  rootSections?
  WS?
  EOF;

heading:
  MAGIC_HEADING;

rootSections:
  section (WS? section)*;

section:
  sectionName
  WS?
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
  section | term;

term:
  word | number | string;

string:
  STRING  ( WS? PLUS WS? STRING )*;

word:
  (ANY_LETTERS | CYR_LETTERS | SYMBOL)+
  (ANY_LETTERS | CYR_LETTERS | SYMBOL | DIGITS | DOT | DASH | PLUS)*;

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

STRING:
  '"' .*? '"';

SYMBOL:
  [,&:?№*~`§±|;!#%=\\/@$] | LEFT_BRACKET | RIGHT_BRACKET;

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
  [ \t]+;

NEWLINE:
  [\n\r] -> skip;
