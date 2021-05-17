/*
 * Author: Kyra Samuel
 * File: main.cpp
 * Date: 05/17/2021
 * Description: main function creates a Homework1 obj and prints the result of double Homework1::powSquared()
 */

#include </home/ec2-user/environment/Homework1/cpp/homework1.h>
#include <iostream>

using namespace std;

int main() {
    Homework1 homework1; // create Homework1 obj
    double result = homework1.powSquared(); // get result from powSquared()
    cout << "Result is: " << result << endl; // print result
}