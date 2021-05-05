/*
 * Kyra Samuel
 * CMIS 141
 * 03/16/2021
 * Assignment 3
 *
 * A Java program to calculate student’s final course grade.
 */

import java.io.BufferedReader; 
import java.io.IOException; 
import java.io.InputStreamReader; 

import java.util.*;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import java.text.DecimalFormat;

class Main {
  static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in)); 
  
  static LinkedHashMap<Integer, LinkedHashMap> students 
            = new LinkedHashMap<Integer, LinkedHashMap>(); // student database
            
  // create an array list to store tokens
  static ArrayList<Integer> tokenArrayList = new ArrayList<Integer>();
  static int count = 0;

  static void restart() throws IOException {
    // method for restarting program
    System.out.print("Do you want to enter another student's data? Yes/No => ");
    String answer = reader.readLine();
    proceed(answer, true);
  }
  
  static void tokenize(String grades, LinkedHashMap<String, String> profile) throws IOException {
    /* 
     * This method adds tokens to an array list, checks if its valid then calculates the grade
     *
     */
  
    String regexPattern = "\\d+"; // pattern for regex this is wrong got deleted

    Pattern pattern = Pattern.compile(regexPattern); // regex pattern obj
    Matcher matcher = pattern.matcher(grades); // matcher obj

    while(matcher.find()) { // if match found, add to ArrayList
      String currentToken = matcher.group();
      try {
        int token = Integer.parseInt(currentToken); // convert to int
        tokenArrayList.add(token);
      } catch(Exception e) {
        System.out.println("Error: Enter numbers only.\n");
        tokenArrayList.clear(); // clear token list
        getInput(profile);
      }
    }
    
    if(validInput()) { // if tokens are valid
      count++; // increase student count, for student key
      students.put(count, setProfile(profile)); // call setProfile and add as value
      calculate();// calculate the final grade
    }
    else {
      System.out.println("Invalid Input.");
      tokenArrayList.clear(); // clear token list
      getInput(profile);
    }
  }
  
  static LinkedHashMap setProfile(LinkedHashMap<String, String> profile) {
    /*
     * This method sets the value for the students profile
     *
     */
     
    String A1, A2, Ex, P;
    
    // set values from tokenArrayList, convert to String
    
    A1 = tokenArrayList.get(0).toString();
    A2 = tokenArrayList.get(1).toString();
    Ex = tokenArrayList.get(2).toString();
    P = tokenArrayList.get(3).toString();
    
    // add values to profile
    
    profile.put("A1", A1);
    profile.put("A2", A2);
    profile.put("Ex", Ex);
    profile.put("P", P);

    return profile;
  }

  static boolean validInput() {
    // method checks for valid ArrayList size (4 grades) and valid grades (0-100)
    if (tokenArrayList.size() == 4) {
      for (int token : tokenArrayList) { // check every token
        if (token < 0 || token > 100) return false;
      }
      return true; 
    }
    return false;
  }

  static void getInput() throws IOException  {
    /*
     * This method gets the users input then tokenizes the grades
     */
  
    LinkedHashMap<String, String> profile 
            = new LinkedHashMap<String, String>();
    
    System.out.print("Enter student's name => ");
    String name = reader.readLine();
    
    System.out.print("Enter student's grades separated by space: A1 A2 Ex P => ");
    String grades = reader.readLine();

    profile.put("Name", name);
    tokenize(grades, profile);
  }

  static void getInput(LinkedHashMap<String, String> profile) throws IOException {
    /*
     * getInput() overloaded, in the case of invalid grade inputs - gets newGrades for same student
     */
    System.out.print("Enter student's grades separated by space: A1 A2 Ex P => ");
    String newGrades = reader.readLine();
    
    tokenize(newGrades, profile);
  }
  
  static void calculate() {
    // calculates finalGrade given formula
    int A1, A2, Ex, P;
    double finalGrade;

    String name, sA1, sA2, sEx, sP;
    
    DecimalFormat df = new DecimalFormat("###.##");

    try {
      LinkedHashMap<String, String> profile = students.get(count);
      // get students profile
      name = profile.get("Name");
      sA1 = profile.get("A1");
      sA2 = profile.get("A2");
      sEx = profile.get("Ex");
      sP = profile.get("P");
      
      // convert grades to int
      A1 = Integer.parseInt(sA1);
      A2 = Integer.parseInt(sA2);
      Ex = Integer.parseInt(sEx);
      P = Integer.parseInt(sP);

      finalGrade = (A1*0.25) + (A2*0.25) + (Ex*0.4) + (P*0.1);

      System.out.printf("Student Name: %s A1=%s A2=%s Exam=%s Participation=%s\nFinal course grade=%s\n", name, sA1, sA2, sEx, sP, df.format(finalGrade));
      restart(); // prompt user to restart
    } catch(Exception e) {
      System.out.println("Error Calculating: " + e);
    }
  }

  static void proceed(String answer, boolean restart) throws IOException {
    // method that checks for yes or no to proceed or restart
    if (answer.equals("Yes") || answer.equals("yes")) {
      if (restart) tokenArrayList.clear();
      getInput();
    }
    else if (answer.equals("No") || answer.equals("no")) {
      reader.close();
      if (restart) System.out.println("Thank you for using the grade calculation program");
      else System.out.println("Goodbye!");
    } else {
      System.out.print("Please response Yes/No => ");
      String input = reader.readLine();
      proceed(input, restart);
    }
  }
  
  public static void main(String[] args) throws IOException  {
    System.out.print("Welcome to the grade calculation program.\nDo you want to enter student's data? Yes/No => ");
    String answer = reader.readLine();;
    proceed(answer, false);
  }
}