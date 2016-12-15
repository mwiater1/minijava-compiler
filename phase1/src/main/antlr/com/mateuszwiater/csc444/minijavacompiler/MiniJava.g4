/*
The original MiniJava grammar with the following additions:
- Switch Case
*/
grammar MiniJava;

goal
:	mainClass classDeclaration* EOF ;

mainClass
:	'class' Identifier LCB 'public' 'static' 'void' 'main' LP 'String' LSB RSB Identifier RP LCB statement RCB RCB ;

classDeclaration
:	'class' Identifier ( extendClass )? LCB fieldDeclaration* methodDeclaration* RCB ;

extendClass
: 'extends' Identifier ;

fieldDeclaration
:	varDeclaration ;

varDeclaration
:	var SEMICOLON ;

methodDeclaration
:	'public' var LP parameterList? RP LCB methodBody RCB ;

parameterList
:   parameter (DELIMITER parameter)* ;

parameter
:   var ;

var
: identifier Identifier ;

methodBody
:	localDeclaration* statement* RETURN expression SEMICOLON ;

localDeclaration
:	varDeclaration ;

identifier
:   INT
|	BOOLEAN
|   Identifier
|	INT LSB RSB ;

statement
:   LCB statement* RCB                                      #nestedStatement
|   SOUT LP  expression RP SEMICOLON                        #printStatement
|	WHILE LP expression RP whileBlock                       #whileStatement
|	Identifier EQ expression SEMICOLON                      #variableAssignmentStatement
|   IF LP expression RP ifBlock ELSE elseBlock              #ifElseStatement
|	Identifier LSB expression RSB EQ expression SEMICOLON   #arrayAssignmentStatement
|   SWITCH LP expression RP LCB switchCaseBlock RCB         #switchStatement;

ifBlock
:	statement ;

elseBlock
:	statement ;

whileBlock
:	statement ;

switchCaseBlock
:   switchCase* defaultSwitchCase;

switchCase
:   CASE IntegerLiteral COLON statement ( CONTINUE SEMICOLON )?;

defaultSwitchCase
:   DEFAULT COLON statement;

expression
:   THIS                                                                        #thisExpression
|   Identifier                                                                  #identifierExpression
|   IntegerLiteral                                                              #intLitExpression
|   BooleanLiteral                                                              #booleanLitExpression
|   NOT expression                                                              #notExpression
|   LP expression RP                                                            #parenExpression
|   NEW Identifier LP RP                                                        #objectInstantiationExpression
|   expression DOT LENGTH                                                       #arrayLengthExpression
|   NEW INT LSB expression RSB                                                  #arrayInstantiationExpression
|   expression LSB expression RSB                                               #arrayAccessExpression
|   expression LT expression                                                    #ltExpression
|   expression AND expression                                                   #andExpression
|   expression PLUS expression                                                  #addExpression
|   expression TIMES expression                                                 #mulExpression
|   expression MINUS expression                                                 #subExpression
|   expression DOT Identifier LP ( expression ( DELIMITER expression )* )? RP   #methodCallExpression ;

// Operators
LT:'<';
NOT:'!';
AND:'&&';
PLUS:'+';
MINUS:'-';
TIMES:'*';

// Brackets
LP:'(';
RP:')';
LSB:'[';
RSB:']';
LCB: '{';
RCB: '}';

// Types
INT: 'int' ;
BOOLEAN: 'boolean' ;

// Constants
EQ: '=';
IF: 'if';
DOT: '.' ;
NEW: 'new' ;
COLON: ':' ;
THIS: 'this' ;
ELSE: 'else' ;
CASE: 'case' ;
WHILE: 'while';
DELIMITER: ',';
SEMICOLON: ';';
LENGTH:'length';
RETURN: 'return';
SWITCH: 'switch' ;
DEFAULT: 'default' ;
CONTINUE: 'continue' ;
SOUT: 'System.out.println';

BooleanLiteral
:	'true'
|	'false' ;

Identifier
:	JavaLetter JavaLetterOrDigit*
;

fragment
JavaLetter
:	[a-zA-Z$_] // these are the 'java letters' below 0xFF
;

fragment
JavaLetterOrDigit
:	[a-zA-Z0-9$_] // these are the 'java letters or digits' below 0xFF
;

IntegerLiteral
:	DecimalIntegerLiteral
;

fragment
DecimalIntegerLiteral
:	DecimalNumeral IntegertypeSuffix? ;

fragment
IntegertypeSuffix
:	[lL] ;

fragment
DecimalNumeral
:	'0'
|	NonZeroDigit (Digits? | Underscores Digits) ;

	fragment
	Digits
	:	Digit (DigitsAndUnderscores? Digit)? ;

	fragment
	Digit
	:	'0'
	|	NonZeroDigit ;

	fragment
	NonZeroDigit
	:	[1-9] ;

	fragment
	DigitsAndUnderscores
	:	DigitOrUnderscore+
	;

	fragment
	DigitOrUnderscore
	:	Digit
	|	'_' ;

	fragment
	Underscores
	:	'_'+ ;

WS
:   [ \r\t\n]+ -> skip ;

MULTILINE_COMMENT
:  '/*' .*? '*/' -> skip ;

LINE_COMMENT
:  '//' .*? '\n' -> skip ;