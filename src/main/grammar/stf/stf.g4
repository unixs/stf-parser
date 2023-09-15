grammar stf;

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
  LETTER (LETTER | DIGIT)*;

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
  (LETTER | CYR_LETTER | SYMBOL)+
  (LETTER | CYR_LETTER | DIGIT | DOT | DASH | PLUS | SYMBOL)*;

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

LETTER:
  [a-zA-Z_а-я];

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
