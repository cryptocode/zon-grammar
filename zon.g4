grammar zon;

config: DOT LBRACE tags* RBRACE COMMA? EOF;
tags : name | version | dependencies;
name: DOT Name EQ Value COMMA;
version: DOT Version EQ Value COMMA;
dependencies : DOT Dependencies EQ DOT LBRACE dependency* RBRACE COMMA;
dependency: DOT dependencyname EQ DOT LBRACE assignment* RBRACE COMMA;
assignment: DOT (Url | Hash) EQ Value COMMA;
dependencyname: Identifier;

Name : 'name';
Version : 'version';
Dependencies : 'dependencies';
Url: 'url';
Hash: 'hash';

Value :	'"' StringCharacters? '"';
fragment StringCharacters: StringCharacter+;
fragment StringCharacter: ~["];

Identifier : [A-Za-z_][A-Za-z0-9_]*;
Newline : ('\r' '\n'? | '\n')+ -> skip;
Space : [ \t]+ -> skip;
LineComment : '//' ~[\r\n]* -> skip;
DOT : '.';
COMMA : ',';
LBRACE: '{';
RBRACE: '}';
EQ : '=';
WS : [ \t\r\n\u000C]+ -> skip;