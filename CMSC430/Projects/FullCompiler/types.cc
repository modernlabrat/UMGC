// Compiler Theory and Design
// Duane J. Jarc

// This file contains the bodies of the type checking functions

#include <string>
#include <vector>
#include <cmath>

using namespace std;

#include "types.h"
#include "listing.h"

void checkAssignment(Types lValue, Types rValue, string message)
{
  if (lValue == INT_TYPE && rValue == REAL_TYPE)
    appendError(GENERAL_SEMANTIC, "Illegal Narrowing Variable Initialization");
	else if (lValue != MISMATCH && rValue != MISMATCH && lValue != rValue)
		appendError(GENERAL_SEMANTIC, "Type Mismatch on " + message);
  
}

Types checkArithmetic(Types left, Types right)
{
	if (left == MISMATCH || right == MISMATCH)
		return MISMATCH;

	if (left == BOOL_TYPE || right == BOOL_TYPE)
	{
		appendError(GENERAL_SEMANTIC, "Numeric Type Required");
		return MISMATCH;
	}

  if (left == REAL_TYPE || right == REAL_TYPE) 
    return REAL_TYPE;
  
	return INT_TYPE;
}

Types checkLogical(Types left, Types right)
{
	if (left == MISMATCH || right == MISMATCH)
		return MISMATCH;
	if (left != BOOL_TYPE || right != BOOL_TYPE)
	{
		appendError(GENERAL_SEMANTIC, "Boolean Type Required");
		return MISMATCH;
	}	
		return BOOL_TYPE;
	return MISMATCH;
}

Types checkNegation(Types right)
{
	if (right == MISMATCH)
		return MISMATCH;
	if  (right != BOOL_TYPE)
	{
		appendError(GENERAL_SEMANTIC, "Boolean Type Required");
		return MISMATCH;
	}	
		return BOOL_TYPE;
	return MISMATCH;
}

Types checkRemainder(Types right)
{
	if  (right != INT_TYPE)
	{
		appendError(GENERAL_SEMANTIC, "Remainder Operator Requires Integer Operands");
		return MISMATCH;
	}	
		return INT_TYPE;
	return MISMATCH;
}

Types checkRelational(Types left, Types right)
{
	if (checkArithmetic(left, right) == MISMATCH)
		return MISMATCH;
	return BOOL_TYPE;
}

void checkConditional(Types expression, Types then_statement, Types else_statement)
{
  if (expression != BOOL_TYPE)
		appendError(GENERAL_SEMANTIC, "If Expression Must Be Boolean");
  
  if (then_statement != else_statement) 
    appendError(GENERAL_SEMANTIC, "If-Then Type Mismatch");
}


double evaluateReduction(Operators operator_, double head, double tail)
{
	if (operator_ == ADD)
		return head + tail;
	else
    return head * tail;
}

double evaluateRelational(double left, Operators operator_, double right)
{
	double result;
	switch (operator_)
	{
		case LESS:
			result = left < right;
			break;
    case MORE:
			result = left > right;
			break;
    case LESS_EQUAL:
			result = left <= right;
			break;
    case MORE_EQUAL:
			result = left >= right;
			break;
    case EQUALS:
			result = left == right;
			break;
    case NOT_EQUALS:
			result = left != right;
			break;
	}
	return result;
}

double evaluateArithmetic(double left, Operators operator_, double right)
{
	double result;
	switch (operator_)
	{
		case ADD:
			result = left + right;
			break;
		case MULTIPLY:
			result = left * right;
			break;
    case SUBTRACT:
			result = left - right;
			break;
    case DIVIDE:
			result = left / right;
			break;
    case EXP:
			result = pow(left, right);
			break;
    case REM:
			result = fmod(left, right);
			break;
	}
	return result;
}

