/* Compiler Theory and Design
   Dr. Duane J. Jarc 
   Modified By: Kyra Samuel
   Date: 11/20/2021 */

%{

#include <string>

using namespace std;

#include "listing.h"

int yylex();
void yyerror(const char* message);

%}

%define parse.error verbose

%token IDENTIFIER
%token INT_LITERAL REAL_LITERAL BOOL_LITERAL

%token ADDOP MULOP RELOP ANDOP REMOP EXPOP OROP NOTOP

%token BEGIN_ BOOLEAN END ENDREDUCE FUNCTION INTEGER IS REDUCE RETURNS
%token IF THEN ELSE ENDIF ENDCASE CASE OTHERS ARROW WHEN REAL

%%

function:	
	function_header_ variables body ;
	
function_header:	
	FUNCTION IDENTIFIER parameters RETURNS type ';' ;

function_header_:	
	function_header |
  error ';' ;

variable_:
  variable |
  error ';' ;

variable:
  IDENTIFIER ':' type IS statement_ ;

variables:
  variables variable_ |
  ;

parameter: 
  IDENTIFIER ':' type |
  IDENTIFIER ':' type ',' ;

parameters:
  parameters parameter | ;

type:
	INTEGER |
  REAL |
	BOOLEAN ;

body:
	BEGIN_ statement_ END ';' ;
    
statement_:
	statement ';' |
	error ';' ;

statements:
  statements statement_ |
  statement_ ;
	
statement:
	expression |
	REDUCE operator reductions ENDREDUCE |
  IF expression THEN statements ELSE statements ENDIF |
  CASE expression IS cases OTHERS ARROW statement_ ENDCASE 
  ;

operator:
	ADDOP | 
  MULOP ;

cases:
  cases case |
  ;

case: 
  case WHEN INT_LITERAL ARROW statement_ |
  ;

reductions:
	reductions statement_ |
	;

expression:
	expression OROP logical |
	logical ;

logical:
  logical ANDOP relation |
  relation ;

relation:
	relation RELOP term |
	term
  ;

term:
	term ADDOP factor |
	factor ;
      
factor:
	factor MULOP primary |
  factor REMOP power |
	power ;

power:
  unary EXPOP power |
  unary ;

unary:
  NOTOP unary |
  primary ;

primary:
	'(' expression ')' |
	INT_LITERAL | REAL_LITERAL | BOOL_LITERAL |
	IDENTIFIER 
  ;

%%

void yyerror(const char* message)
{
	appendError(SYNTAX, message);
}

int main(int argc, char *argv[])    
{
	firstLine();
	yyparse();
	lastLine();
	return 0;
} 
