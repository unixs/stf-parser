grammar stf;

@header {
  package net.unixcode.rts.parser.antlr;
}

stf:
  heading
  space?
  node
  space?
  node*
  space?
  EOF;

heading:
  MAGIC_HEADING_1;

node:
  nodeName
  space?
  LEFT_PAREN
  space?
  nodeList?
  space?
  RIGHT_PAREN;

space:
  WS+;

nodeName:
  ANY_LETTER (ANY_LETTER | DIGIT)*;

nodeList:
  nodeListItem (nodeListDelim nodeListItem)*;

nodeListDelim:
  space;

nodeListItem:
  node | term;

term:
  word | number | string;

string:
  STRING space? ( PLUS space? STRING )*;

word:
  (ANY_LETTER | CYR_LETTER | SYMBOL)+
  (ANY_LETTER | CYR_LETTER | SYMBOL | DIGIT | DOT | DASH | PLUS)*;

number:
  DASH? DIGIT+ (DOT DIGIT+)?;


MAGIC_HEADING_1:
  'SIMISA@@@@@@@@@@JINX0t1t______';

LEFT_PAREN:
  '(';

RIGHT_PAREN:
  ')';

STRING:
  '"' .*? '"';

SYMBOL:
  [,&:?|@$] | LEFT_BRACKET | RIGHT_BRACKET;

LEFT_BRACKET:
  '[';

RIGHT_BRACKET:
  ']';

PLUS:
  '+';

ANY_LETTER:
  LETTER | CAPITAL_LETTER;

CAPITAL_LETTER:
  [A-Z];

LETTER:
  [a-z_а-я];

CYR_LETTER:
  [а-яА-ЯёЁ];

DIGIT:
  [0-9];

DASH:
  '-';

DOT:
  '.';

WS:
  [ \t];

NEWLINE:
  [\n\r] -> skip;
