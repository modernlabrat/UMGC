/*
 * Author: Kyra Samuel
 * File: homework1.h
 * Date: 05/17/2021
 * Description: a header file called homework1 that includes the powSqaured function
 */
 
#include <iostream>
#include <cmath>
using namespace std;

class Homework1 {
    public:
        double powSquared() {
            // a method that prompts the user for a base and exponent then squares the result
            double base, exponent;
    
            cout << "Enter base: "; // example: base = 2

            while (1) { // check if valid double
              if (cin >> base)
                  break;
              else {
                  cout << "Please input a valid double value: ";
                  cin.clear();
                  while (cin.get() != '\n') ; // empty loop
              }
            }
            
            cout << "Enter exponent: "; // example: base = 2
            while (1) {
              if (cin >> exponent)
                  break;
              else {
                  cout << "Please input a valid double value: ";
                  cin.clear();
                  while (cin.get() != '\n') ; // empty loop
              }
            }
    
            double result = pow(pow(base, exponent), 2); // example: base(2)^exponent(2) = 4; therefore, result = 4 squared = 16
    
            return result;
        }
};