import java.util.Scanner;
import java.text.DecimalFormat;
import java.util.ArrayList;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

class Main {
  // create an array list to store tokens
  static ArrayList<String> tokenArrayList = new ArrayList<String>();
  static int currentToken = 0; // indicates index 0 in tokenArrayList

  static double output, operand1, operand2;
  static String operator;

  static boolean isAnOperand(String token) {
    try {
      double newToken = Double.parseDouble(token);
    } catch (NumberFormatException e) {
      return false;
    }
    return true;
  }
  // checks if the token is an operator
  static boolean isAnOperator(String token) {
    if (!(token.equals("/") || token.equals("*") || token.equals("+") || token.equals("-")))
      return false;
    else 
      return true;
  }

  static void tokenizeInput(String input) {
    // tokenize the user input, add the tokens to an ArrayList
    String regexPattern = "\\d+"; // pattern for regex this is wrong got deleted

    Pattern pattern = Pattern.compile(regexPattern); // regex pattern obj
    Matcher matcher = pattern.matcher(input); // matcher obj

    while(matcher.find()) { // if match found, add to ArrayList
      String currentToken = matcher.group();
      tokenArrayList.add(currentToken);
    }
  }

  static boolean parseExpression() {
    // nested if-statements to parse a simple arithemtic expression (2 operands, 1 operator)
    if(tokenArrayList.size() == 3) {
      String firstToken = tokenArrayList.get(currentToken);
    
      if(isAnOperand(firstToken)) {
        String nextToken = getNextToken();

        if(isAnOperator(nextToken)) {
          String lastToken = getNextToken();

          if(isAnOperand(lastToken)) return true;
        }
      }
    }
  
    return false;
  }

  static void compute() {
    // method computes the expression and gives the user an output
    try {
      operand1 = Double.parseDouble(tokenArrayList.get(0));
      operator = tokenArrayList.get(1);
      operand2 = Double.parseDouble(tokenArrayList.get(2));
      
      switch(operator) { // depends on the operator
        case "+":
          output = operand1 + operand2;
          break;
        case "-":
          output = operand1 - operand2;
          break;
        case "*":
          output = operand1 * operand2;
          break;
        case "/":
          output = operand1 / operand2;
          break;
      }

      System.out.printf("Result: %s %s %s = %s", operand1, operator, operand2, output); // output result
    } catch(Exception e) {
      // catch the Exception - if ArrayList doesn't have at least 3 values - will throw Exception
      invalidExpression(); 
    }
  }

  static String getNextToken() {
    currentToken++;
    return tokenArrayList.get(currentToken);
  }

  static void run() {
    // gets the user input, tokenize the input, parse the expression, then compute
    Scanner input = new Scanner(System.in);

    System.out.println("Please enter a single expression without spaces: i.e 4+5: ");
    String expression = input.next();

    tokenizeInput(expression);

    try {
      if (parseExpression()) {
        compute();
        input.close();
      } else invalidExpression();
    } catch (Exception e) {
      invalidExpression();
    }
  }

  static void invalidExpression() {
    // if invalid expression, do this:
    System.out.println("Invalid Expression: cannot contain letters, characters, more than one operator, or more than 2 operands.");
    tokenArrayList.clear();
    currentToken = 0;
    run();
  }

  public static void main(String[] args) {
    run(); // call run()
  }
}