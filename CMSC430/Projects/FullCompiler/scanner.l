/* Compiler Theory and Design
   Duane J. Jarc */

/* This file contains flex input file */

%{
#include <string>

using namespace std;

#include "types.h"
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
real 		{digit}+\.{digit}*([Ee][+-]?{digit}+)?
punc		[\(\),:;]
%%

{ws}		{ ECHO; }
{comment}	{ ECHO; nextLine();}
{line}		{ ECHO; nextLine();}
"<"		{ ECHO; yylval.oper = LESS; return(RELOP); }
">"		{ ECHO; yylval.oper = MORE; return(RELOP); }
"<="		{ ECHO; yylval.oper = LESS_EQUAL; return(RELOP); }
">="		{ ECHO; yylval.oper = MORE_EQUAL; return(RELOP); }
"="		{ ECHO; yylval.oper = MORE_EQUAL; return(RELOP); }
"/="		{ ECHO; yylval.oper = MORE_EQUAL; return(RELOP); }
"+"		{ ECHO; yylval.oper = ADD; return(ADDOP); }
"-"   { ECHO; yylval.oper = SUBTRACT; return(ADDOP); }
"*"		{ ECHO; yylval.oper = MULTIPLY; return(MULOP); }
"/"		{ ECHO; yylval.oper = DIVIDE; return(MULOP); }
"**" {ECHO; yylval.oper = EXP; return(EXPOP); }
"rem" {ECHO; yylval.oper = REM; return(REMOP); }
"=>" {ECHO; return(ARROW); }
begin		{ ECHO; return(BEGIN_); }
boolean		{ ECHO; return(BOOLEAN); }
end		{ ECHO; return(END); }
endreduce	{ ECHO; return(ENDREDUCE); }
function	{ ECHO; return(FUNCTION); }
integer		{ ECHO; return(INTEGER); }
is		{ ECHO; return(IS); }
reduce		{ ECHO; return(REDUCE); }
returns		{ ECHO; return(RETURNS); }
and		{ ECHO; return(ANDOP); }
real { ECHO; return(REAL); }
if  { ECHO; return(IF); }
then { ECHO; return(THEN); }
when { ECHO; return(WHEN); }
others { ECHO; return(OTHERS); }
case { ECHO; return(CASE); } 
else { ECHO; return(ELSE); }
endcase { ECHO; return(ENDCASE); }
endif { ECHO; return(ENDIF); }
true { ECHO; yylval.value = atoi(yytext); yylval.type = BOOL_TYPE; return(BOOL_LITERAL); }
false { ECHO; yylval.value = atoi(yytext); yylval.type = BOOL_TYPE; return(BOOL_LITERAL); }
{real} { ECHO; yylval.value = atof(yytext); yylval.type = REAL_TYPE; return(REAL_LITERAL); }
or { ECHO; return(OROP); }
not { ECHO; return(NOTOP); }
{id}		{ ECHO; yylval.iden = (CharPtr)malloc(yyleng + 1); 
			strcpy(yylval.iden, yytext); return(IDENTIFIER);}
{int}		{ ECHO; yylval.value = atoi(yytext); yylval.type = INT_TYPE; return(INT_LITERAL); }
{punc}		{ ECHO; return(yytext[0]); }
.		{ ECHO; appendError(LEXICAL, yytext); }

%%
