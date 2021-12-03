// Compiler Theory and Design
// Dr. Duane J. Jarc

// This file contains the bodies of the functions that produces the compilation
// listing

#include <cstdio>
#include <string>
#include <iostream>

using namespace std;

#include "listing.h"

static int lineNumber;
static string error = "";
static int totalErrors = 0;
static int lexicalErrors = 0;
static int syntacticErrors = 0;
static int semanticErrors = 0;

static void displayErrors();

void firstLine()
{
	lineNumber = 1;
	printf("\n%4d  ",lineNumber);
}

void nextLine()
{
	lineNumber++;
	printf("%4d  ",lineNumber);
  displayErrors();
}

int lastLine()
{
	printf("\r");
	displayErrors();
	printf("     \n");

	if (totalErrors == 0)
		printf("Compiled Successfully");
	else {
		printf("Lexical Errors %d\n", lexicalErrors);
		printf("Syntax Errors %d\n", syntacticErrors);
		printf("Semantic Errors %d\n", semanticErrors);
	}

	return totalErrors;
}
    
void appendError(ErrorCategories errorCategory, string message)
{
	string messages[] = { "Lexical Error, Invalid Character ", "",
		"Semantic Error, ", "Semantic Error, Duplicate Identifier: ",
		"Semantic Error, Undeclared " };

	error = messages[errorCategory] + message;

	switch(errorCategory) {
		case LEXICAL:
			lexicalErrors++;
			break;
		case GENERAL_SEMANTIC:
			semanticErrors++;
			break;
		case SYNTAX:
			syntacticErrors++;
			break;
    default:
      semanticErrors++;
      break;
	}

	totalErrors++;
}

void displayErrors()
{
	if (error != "")
		printf("%s\n", error.c_str());
	error = "";
}

