/* Compiler Theory and Design
   Dr. Duane J. Jarc */

/* This file contains flex input file */

%{
#include <string>
#include <vector>

using namespace std;

#include "listing.h"
#include "tokens.h"

%}

%option noyywrap

ws		[ \t\r]+
comment		\-\-.*\n
line		[\n]
id		[A-Za-z][A-Za-z0-9]*
digit		[0-9]
int		{digit}+
punc		[\(\),:;]
real 		{digit}+\.{digit}*([Ee][+-]?{digit}+)?
%%

{ws}		{ ECHO; }
{comment}	{ ECHO; nextLine();}
{line}		{ ECHO; nextLine();}
"+"		{ ECHO; return(ADDOP); }
"-" { ECHO; return(ADDOP); }
"/" { ECHO; return(MULOP); }
"*"		{ ECHO; return(MULOP); }
"<"		{ ECHO; return(RELOP); }
">"		{ ECHO; return(RELOP); }
"<="		{ ECHO; return(RELOP); }
">="		{ ECHO; return(RELOP); }
"="		{ ECHO; return(RELOP); }
"/="		{ ECHO; return(RELOP); }
"=>" {ECHO; return(ARROW); }
"**" {ECHO; return(EXPOP); }
begin		{ ECHO; return(BEGIN_); }
boolean		{ ECHO; return(BOOLEAN); }
end		{ ECHO; return(END); }
endreduce	{ ECHO; return(ENDREDUCE); }
function	{ ECHO; return(FUNCTION); }
integer		{ ECHO; return(INTEGER); }
is		{ ECHO; return(IS); }
reduce		{ ECHO; return REDUCE; }
returns		{ ECHO; return(RETURNS); }
real { ECHO; return(REAL); }
if  { ECHO; return(IF); }
or { ECHO; return(OROP); }
not { ECHO; return(NOTOP); }
case { ECHO; return(CASE); } 
else { ECHO; return(ELSE); }
endcase { ECHO; return(ENDCASE); }
endif { ECHO; return(ENDIF); }
others { ECHO; return(OTHERS); }
real { ECHO; return(REAL); }
then { ECHO; return(THEN); }
when { ECHO; return(WHEN); }
rem { ECHO; return(REMOP); }
and		{ ECHO; return(ANDOP); }
{id}		{ ECHO; return(IDENTIFIER);}
{int}		{ ECHO; return(INT_LITERAL); }
{punc}		{ ECHO; return(yytext[0]); }
{real} { ECHO; return(REAL_LITERAL); }
true { ECHO; return(BOOL_LITERAL); }
false { ECHO; return(BOOL_LITERAL); }
.		{ ECHO; appendError(LEXICAL, yytext); }

%%
