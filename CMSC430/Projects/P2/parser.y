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
	function_header_ optional_variables body ;

function_header_:	
	function_header |
  error ';' ;
	
function_header:	
	FUNCTION IDENTIFIER parameters RETURNS type ';' ;

optional_variables:
  optional_variables variable_ |
  ;

variable_:
  variable |
  error ';' ;

variable:
  IDENTIFIER ':' type IS statement_ ;

parameters:
  parameters parameter | 
  ;

parameter: 
  IDENTIFIER ':' type |
  IDENTIFIER ':' type ',' ;

type:
	INTEGER |
  REAL |
	BOOLEAN ;

body:
	BEGIN_ statement_ END ';' ;
	
statement:
	expression |
  IF expression THEN statements ELSE statements ENDIF  |
  CASE expression IS cases OTHERS ARROW statement_ ENDCASE |
	REDUCE operator reductions ENDREDUCE ;

statements:
  statements statement_ |
  statement_ ;
    
statement_:
	statement ';' |
	error ';';

cases:
  cases case |
  ;

case:
  WHEN INT_LITERAL ARROW statement_ ;

operator:
	ADDOP | MULOP | REMOP |  RELOP | ANDOP | NOTOP | OROP |  EXPOP ;

reductions:
	reductions statement_ ;

expression:
  expression OROP logical |
  logical ;

logical:
  logical ANDOP relation |
  relation ;

relation:
	relation RELOP term |
	term ;

term:
	term ADDOP factor |
	factor ;
      
factor:
	factor MULOP power |
  factor REMOP power |
  power
  ;

power:
  negate EXPOP power |
  negate ;

negate:
  NOTOP negate |
  primary

primary:
	'(' expression ')' |
	INT_LITERAL | REAL_LITERAL | BOOL_LITERAL |
	IDENTIFIER ;
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
