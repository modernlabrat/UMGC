#include <iostream>
#include <fstream>
#include <sstream>
#include <string>
#include <vector>
using namespace std;

#include "expression.h"
#include "subexpression.h"
#include "symboltable.h"
#include "parse.h"

SymbolTable symbolTable;

void parseAssignments(stringstream& in);

int main()
{ 
  string test;
  while(test != "0") {
    cout << "Enter '0' to stop the program.\nEnter the name of txt file: ";
    cin >> test;
    fstream inputfile;
    inputfile.open(test+".txt", ios::in);
    Expression* expression;
    char paren, comma;

    while(inputfile.is_open()) {
      string line;
      while(getline(inputfile, line)) {
        stringstream in(line, ios_base::in);   
        in >> paren;
        cout << line << " ";
        expression = SubExpression::parse(in);
        in >> comma;
  
        parseAssignments(in);
        cout << "Value= " << expression->evaluate() << endl;
        symbolTable.clear();
      }

      inputfile.close();
    }
  }
  return 0;
}

void parseAssignments(stringstream& in)
{
  char assignop, delimiter;
  string variable;
  double value;
  do
  {
      variable = parseName(in);
      in >> ws >> assignop >> value >> delimiter;
      symbolTable.insert(variable, value);
  }
  while (delimiter == ',');
}
   
