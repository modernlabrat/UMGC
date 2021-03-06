#include <iostream>
#include <sstream>
using namespace std;

#include "expression.h"
#include "subexpression.h"
#include "operand.h"
#include "plus.h"
#include "minus.h"
#include "times.h"
#include "divide.h"
#include "conditional.h"
#include "greaterthan.h"
#include "lessthan.h"
#include "equals.h"
#include "andop.h"
#include "orop.h"
#include "negate.h"

SubExpression::SubExpression(Expression* left, Expression* right)
{
  this->left = left;
  this->right = right;
}

SubExpression::SubExpression(Expression *left, Expression* right, Expression* condition)
{
  this->left = left;
  this->right = right;
  this->condition = condition;
}

SubExpression::SubExpression(Expression* left)
{
  this->left = left;
}

Expression* SubExpression::parse(stringstream& in)
{
    Expression* left;
    Expression* right;
    Expression* condition;

    char operation, paren, ques;
    
    left = Operand::parse(in);
    in >> operation;
    
    if (operation == ':') {
      right = Operand::parse(in);
        in >> ques;
        condition = Operand::parse(in);
        in >> paren;
      return new Conditional(left, right, condition);
    } else if (operation == '!') {
      in >> paren;
      return new Negate(left);
    } else {
      right = Operand::parse(in);
      in >> paren;
    }

    switch (operation)
    {
        case '+':
          return new Plus(left, right);
        case '-':
          return new Minus(left, right);
        case '*':
          return new Times(left, right);
        case '/':
          return new Divide(left, right);
        case '>':
          return new GreaterThan(left, right);
        case '<':
          return new LessThan(left, right);
        case '=':
          return new Equals(left, right);
        case '&':
          return new AndOp(left, right);
        case '|':
          return new OrOp(left, right);
    }
    return 0;
}
        