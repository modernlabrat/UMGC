/* Compiler Theory and Design
   Dr. Duane J. Jarc 
	 Modified By: Kyra Samuel 
   Date: 10/28/2021
*/

/* This file contains flex input file */

%{
#include <cstdio>
#include <string>

using namespace std;

#include "listing.h"
#include "tokens.h"

%}

%option noyywrap

ws		[ \t\r]+
comment		\-\-.*\n
line		[\n]
id		([a-zA-Z][a-zA-Z0-9_]*)
digit		[0-9]
int		{digit}+
punc		[\(\),:;]
%%
bool (true|false)
float ([0-9]+\.0*)(?:.e|E)(?:.\+|\-[0-9]+)

{ws}		{ ECHO; }
{comment}	{ ECHO; nextLine();}
{line}		{ ECHO; nextLine();}
"<"		{ ECHO; return(RELOP); }
"="		{ ECHO; return(RELOP); }
"/="		{ ECHO; return(RELOP); }
">"		{ ECHO; return(RELOP); }
">="		{ ECHO; return(RELOP); }
"<="		{ ECHO; return(RELOP); }
"+"		{ ECHO; return(ADDOP); }
"-" { ECHO; return(ADDOP); }
"/" { ECHO; return(MULOP); }
"*"		{ ECHO; return(MULOP); }
"=>" {ECHO; return(ARROW); }
"**" {ECHO; return(EXPOP); }
begin		{ ECHO; return(BEGIN_); }
boolean		{ ECHO; return(BOOLEAN); }
end		{ ECHO; return(END); }
endreduce	{ ECHO; return(ENDREDUCE); }
function	{ ECHO; return(FUNCTION); }
integer		{ ECHO; return(INTEGER); }
is		{ ECHO; return(IS); }
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
reduce		{ ECHO; return(REDUCE); }
returns		{ ECHO; return(RETURNS); }
and		{ ECHO; return(ANDOP); }
{id}		{ ECHO; return(IDENTIFIER);}
{int}		{ ECHO; return(INT_LITERAL); }
{float} {ECHP; return(REAL_LITERAL); }
{bool} { ECHO; return(BOOL_LITERAL); }
{punc}		{ ECHO; return(yytext[0]); }
.		{ ECHO; appendError(LEXICAL, yytext); }
%%



int main()
{
	firstLine();
	
	FILE *file = fopen("lexemes.txt", "wa"); 
	int token = yylex();
	while (token)
	{
		fprintf(file, "%d %s\n", token, yytext);
		token = yylex();
	}
	lastLine();
	fclose(file);
	return 0;
}