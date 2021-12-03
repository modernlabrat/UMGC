// CMSC 430
// Duane J. Jarc
// Modified By : Kyra Samuel
// Date : 11 /30 / 2021
// This file contains the bodies of the evaluation functions

#include <string>
#include <vector>
#include <cmath>

using namespace std;

#include "values.h"
#include "listing.h"

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

