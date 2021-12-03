// CMSC 430
// Duane J. Jarc

// This file contains function definitions for the evaluation functions

typedef char* CharPtr;
enum Operators {LESS, ADD, SUBTRACT, DIVIDE, MULTIPLY, EXP, REM, MORE, LESS_EQUAL, MORE_EQUAL, EQUALS, NOT_EQUALS};

double evaluateReduction(Operators operator_, double head, double tail);
double evaluateRelational(double left, Operators operator_, double right);
double evaluateArithmetic(double left, Operators operator_, double right);

