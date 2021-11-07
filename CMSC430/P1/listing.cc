// Compiler Theory and Design
// Dr. Duane J. Jarc

// This file contains the bodies of the functions that produces the compilation
// listing

#include <cstdio>
#include <string>
#include <iostream>
#include <queue>

using namespace std;

#include "listing.h"

static int lineNumber;
static string error = "";
static int totalErrors = 0;
static int lexicalErrors = 0;
static int syntacticErrors = 0;
static int semanticErrors = 0;
static queue<string> errors;

static void displayErrors();

void firstLine()
{
	lineNumber = 1;
	printf("\n%4d  ",lineNumber);
}

void nextLine()
{
	displayErrors();
	lineNumber++;
	printf("%4d  ",lineNumber);
}

int lastLine()
{
	printf("\r");
	displayErrors();
	printf("     \n");

	if (totalErrors == 0)
		printf("Compiled Successfully");
	else {
		printf("Lexical Errors ", lexicalErrors);
		printf("Syntax Errors ", syntacticErrors);
		printf("Semantic Errors ", semanticErrors);
	}

	return totalErrors;
}
    
void appendError(ErrorCategories errorCategory, string message)
{
	string messages[] = { "Lexical Error, Invalid Character ", "",
		"Semantic Error, ", "Semantic Error, Duplicate Identifier: ",
		"Semantic Error, Undeclared " };

	error = messages[errorCategory] + message;
	errors.push(error);

	switch(errorCategory) {
		case LEXICAL:
			lexicalErrors++;
			break;
		case GENERAL_SEMANTIC:
			semanticErrors++;
			break;
		case SYNTAX:
			syntacticErrors++;
		}

	totalErrors++;
}

void displayErrors()
{
	while (!errors.empty()) {
		if (errors.front() != "")
			printf("%s\n", errors.front());
		errors.pop();
	}

	printf("\n");
}
