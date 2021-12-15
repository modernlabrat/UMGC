// Compiler Theory and Design
// Duane J. Jarc

// This file contains type definitions and the function
// prototypes for the type checking functions

typedef char* CharPtr;

enum Types {MISMATCH, INT_TYPE, BOOL_TYPE, REAL_TYPE, NAN_TYPE};

void checkAssignment(Types lValue, Types rValue, string message);
void checkNarrowing(Types lValue, Types rValue);
void checkConditional(Types expression, Types then_statement, Types else_statement);

Types checkArithmetic(Types left, Types right);
Types checkLogical(Types left, Types right);
Types checkRelational(Types left, Types right);
Types checkNegation(Types right);
Types checkRemainder(Types right);
Types checkCase(Types cases, Types statement);