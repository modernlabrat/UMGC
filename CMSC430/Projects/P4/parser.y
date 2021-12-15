/* Compiler Theory and Design
   Duane J. Jarc */

%{

#include <string>
#include <vector>
#include <map>
#include <queue>
#include <iostream>

using namespace std;

#include "types.h"
#include "listing.h"
#include "symbols.h"

int yylex();
void yyerror(const char* message);

Symbols<Types> symbols;
queue<Types> cases;
bool validated = true;

void emptyCase() {
  queue<Types> empty;
  if (!cases.empty())
    swap(cases, empty);
}

bool validCase(Types type) {
  if (!cases.empty()) {
    if (cases.front() != type) {
      appendError(GENERAL_SEMANTIC, "Case Types Mismatch");
      validated = false;
      return false;
    }
  } else 
    cases.push(type);
  return true;
}
%}

%define parse.error verbose

%union
{
	CharPtr iden;
	Types type;
}

%token <iden> IDENTIFIER
%token <type> INT_LITERAL REAL_LITERAL BOOL_LITERAL

%token ADDOP MULOP RELOP ANDOP REMOP EXPOP OROP NOTOP

%token BEGIN_ BOOLEAN END ENDREDUCE FUNCTION INTEGER IS REDUCE RETURNS
%token IF THEN ELSE ENDIF ENDCASE CASE OTHERS ARROW WHEN REAL 

%type <type> type statement statement_ reductions expression relation term
	factor primary logical power negate function_header body variable cases case
%%
function:	
	function_header optional_variables body {checkAssignment($1, $3, "on Function Return");}
  ;
	
function_header:	
	FUNCTION IDENTIFIER parameters RETURNS type ';' { $$ = $5;} ;

optional_variables:
  optional_variables variable |
  %empty ;

variable:	
	IDENTIFIER ':' type IS statement_ 
		{symbols.find($1, $$) ? appendError(DUPLICATE_IDENTIFIER, $1) : symbols.insert($1, $3); checkAssignment($3, $5, "Variable Initialization");} ;

parameters:
  parameters parameter | 
  %empty ;

parameter: 
  IDENTIFIER ':' type |
  IDENTIFIER ':' type ',' ;

type:
	INTEGER {$$ = INT_TYPE;} |
  REAL {$$ = REAL_TYPE;} |
	BOOLEAN {$$ = BOOL_TYPE;} ;

body:
	BEGIN_ statement_ END ';' {$$ = $2;} ;
    
statement_:
	statement ';' |
	error ';' {$$ = MISMATCH;} ;
	
statement:
	expression |
  IF expression THEN statement_ ELSE statement_ ENDIF {checkConditional($2, $4, $6); $$ = $2 ? $4 : $6;} |
  CASE expression IS cases OTHERS ARROW statement_ ENDCASE {if ($2 != INT_TYPE) appendError(GENERAL_SEMANTIC, "Case Expression Not Integer"); if (validated) $$ = $4 == NAN_TYPE ? $7 : $4; emptyCase();} |
	REDUCE operator reductions ENDREDUCE {$$ = $3;} ;

cases:
  cases case {$$ = ($1 == NAN_TYPE) ? $2 : $1;} |
  %empty {$$ = NAN_TYPE; } ;

case:
  WHEN INT_LITERAL ARROW statement_ {$$ = $<type>-2 == $2 ? $4 : NAN_TYPE; validCase($4);};

operator:
	ADDOP | MULOP | REMOP |  RELOP | ANDOP | NOTOP | OROP |  EXPOP ;

reductions:
	reductions statement_ {$$ = checkArithmetic($1, $2);} |
	%empty {$$ = INT_TYPE;} ;

expression:
	expression OROP logical {$$ = checkLogical($1, $3);} |
	logical ;

logical:
  logical ANDOP relation {$$ = checkLogical($1, $3);} |
  relation ;

relation:
	relation RELOP term {$$ = checkRelational($1, $3);}|
	term ;

term:
	term ADDOP factor {$$ = checkArithmetic($1, $3);} |
	factor ;

factor:
	factor MULOP power  {$$ = checkArithmetic($1, $3);} |
  factor REMOP power  {$$ = checkArithmetic($1, checkRemainder($3));} |
	power ;

power:
  negate EXPOP power {$$ = checkArithmetic($1, $3);} |
  negate ;

negate:
  NOTOP negate { $$ = checkNegation($2);} |
  primary ;

primary:
	'(' expression ')' {$$ = $2;} |
	INT_LITERAL | REAL_LITERAL | BOOL_LITERAL |
	IDENTIFIER {if (!symbols.find($1, $$)) appendError(UNDECLARED, $1);} ;
    
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
