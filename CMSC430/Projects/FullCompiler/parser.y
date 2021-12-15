/* Compiler Theory and Design
   Duane J. Jarc 
   Modified By: Kyra Samuel
   Date: 11/30/21
*/

%{

#include <iostream>
#include <string>
#include <vector>
#include <map>
#include <cmath>

using namespace std;

#include "values.h"
#include "types.h"
#include "listing.h"
#include "symbols.h"
#include <queue>

int yylex();
void yyerror(const char* message);

Symbols<Types> symbols;

double result;
queue<double> params;
bool popped = false;

double getParam() {
  if (!popped) {
    double param = params.front();
    popped = true;
    return param;
  }

  params.pop();
  return params.front();
}

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
	Operators oper;
	double value;
}

%token <iden> IDENTIFIER
%token <value> INT_LITERAL REAL_LITERAL REAL
%token <oper> ADDOP MULOP REMOP RELOP EXPOP NOTOP
%token ANDOP OROP 
%token BEGIN_ BOOLEAN END ENDREDUCE FUNCTION INTEGER IS REDUCE RETURNS
%token CASE WHEN OTHERS IF THEN ELSE ENDCASE ENDIF ARROW BOOL_LITERAL

%type <value> body statement_ statement reductions expression relation logical term
	factor primary cases statements variable_ parameter function_header_ power parameters case negate 
%type <oper> operator
%%

function:	
	function_header optional_variables body {checkAssignment($1, $3, "Function Return"); result = $3;} ;
	
function_header:	
	FUNCTION IDENTIFIER parameters RETURNS type ';' { $$ = $5;} ;

optional_variables:
  optional_variables variable |
  %empty ;

variable:
  IDENTIFIER ':' type IS statement_ {symbols.find($1, $$) ? appendError(DUPLICATE_IDENTIFIER, $1) : symbols.insert($1, $3); checkAssignment($3, $5, "Variable Initialization");} ;

parameters:
  parameters parameter | 
  %empty ;

parameter: 
  IDENTIFIER ':' type { !params.empty() ? symbols.insert($1, getParam()) : appendError(UNDECLARED, $1);} |
  IDENTIFIER ':' type ',' { !params.empty() ? symbols.insert($1, getParam()) : appendError(UNDECLARED, $1);} ;

type:
	INTEGER {$$ = INT_TYPE;} |
  REAL {$$ = REAL_TYPE;} |
	BOOLEAN {$$ = BOOL_TYPE;} ;

body:
	BEGIN_ statement_ END ';' {$$ = $2;} ;
	
statement:
	expression |
  IF expression THEN statements ELSE statements ENDIF {checkConditional($2, $4, $6); $$ = $2 ? $4 : $6; } |
  CASE expression IS cases OTHERS ARROW statement_ ENDCASE {if ($2 != INT_TYPE) appendError(GENERAL_SEMANTIC, "Case Expression Not Integer"); if (validated) $$ = $4 == NAN_TYPE ? $7 : $4; emptyCase(); $$ = isnan($4) ? $7 : $4; } |
	REDUCE operator reductions ENDREDUCE {$$ = $3;} ;

statements:
  statements statement_ |
  statement_ ;
    
statement_:
	statement ';' |
	error ';' {$$ = MISMATCH;} ;

cases:
  cases case {$$ = isnan($1) ? $2 : $1; } |
  %empty {$$ = NAN;}
  ;

case:
  WHEN INT_LITERAL ARROW statement_ {$$ = $<value>-2 == $2 ? $4 : NAN; $$ = $<type>-2 == $2 ? $4 : NAN_TYPE; validCase($4);} 
  ;

operator:
	ADDOP |
	MULOP |
  REMOP |
  RELOP |
  NOTOP |
  OROP |
  EXPOP ;

reductions:
	reductions statement_ {$$ = evaluateReduction($<oper>0, $1, $2); $$ = checkArithmetic($1, $2);} |
	{$$ = $<oper>0 == ADD ? 0 : 1;} ;

expression:
  expression OROP logical {$$ = $1 || $3;} |
  logical ;

logical:
  logical ANDOP relation {$$ = $1 && $3;} |
  relation ;

relation:
	relation RELOP term {$$ = evaluateRelational($1, $2, $3);} |
	term ;

term:
	term ADDOP factor {$$ = evaluateArithmetic($1, $2, $3);} |
	factor ;
      
factor:
	factor MULOP power {$$ = evaluateArithmetic($1, $2, $3);} |
  factor REMOP power {$$ = evaluateArithmetic($1, $2, $3);} |
  power
  ;

power:
  negate EXPOP power {$$ = evaluateArithmetic($1, $2, $3);} |
  negate ;

negate:
  NOTOP negate {$$ = !$2; } |
  primary

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
  for(int i=1; i < argc; i++)
    params.push(atof(argv[i]));

	firstLine();
  yyparse();

	if (lastLine() == 0)
		cout << "\nResult = " << result << endl;
	return 0;
}