/*
 * Kyra Samuel
 * Assignment1
 * CMIS141
 *
 * A program that calculates a student's new GPA given the current class GPA, number of credits, overall GPA, and overall number of credits.
 */
import java.util.Scanner;
import java.text.DecimalFormat;

class Main {
  static double currentGPA, overallGPA;
  static String studentId;
  static int currentNoOfCredits, overallNoOfCredits;

  static Scanner input = new Scanner(System.in); // scanner obj

  static void getInput() {
    /* Method that gets the input then calls calculateGPA */
    
    System.out.print("Enter student id: "); 
    studentId = input.next(); // get next before enter

    System.out.print("Enter current class grade in GPA format: ");
    String tempClassGPA = input.next();
    currentGPA = checkGPA(checkDouble(tempClassGPA)); // check for valid input

    System.out.print("Enter current class number of credits: ");
    String tempNoCredits = input.next();
    currentNoOfCredits = checkInt(tempNoCredits);

    System.out.print("Enter overall GPA? ");
    String tempOverallGPA = input.next();
    overallGPA = checkGPA(checkDouble(tempOverallGPA));

    System.out.print("Enter overall number of credits earned. ");
    String tempOverallCredits = input.next();
    overallNoOfCredits = checkCredits(checkInt(tempOverallCredits));
    // closing the scanner object
    input.close();

    calculateGPA(); // call calculateGPA()
  }

  static void calculateGPA() {
    DecimalFormat df = new DecimalFormat("0.0");
    // method that uses formula from Assignment 1 pdf to calculate new GPA
    double newGPA = ((currentGPA * currentNoOfCredits ) + (overallGPA * overallNoOfCredits)) / ( currentNoOfCredits + overallNoOfCredits);

    System.out.printf("STUDENT DATA:\nStudent id: %s\nCurrent class GPA: %s\nCurrent class credits: %s\nOverall GPA: %s\nOverall credits: %s\n\nNEW GPA: %s", studentId, currentGPA, currentNoOfCredits, overallGPA, overallNoOfCredits, df.format(newGPA));
  }

  static int checkInt(String num) {
    /* Simple check for input given a number  */
    try {
      return Integer.parseInt(num);
    } catch(Exception e) {
      // if not, ask for a new number
      System.out.print("Please enter a whole number: ");
      String tempNum = input.next();
      return checkInt(tempNum); // check the num again and again and again!
    }
  }

  static double checkDouble(String num) {
    /* Simple check input given a number */
    try {
      return Double.parseDouble(num);
    } catch(Exception e) {
      // if not, ask for a new number
      System.out.print("Please enter a valid number: ");
      String tempNum = input.next();
      return checkDouble(tempNum); // check the num again and again and again!!!
    }
  }

  static double checkGPA(double num) {
    // up to 4.0 GPA for simplicity 
    if (num > 4 || num < 0) {
      System.out.print("Please enter a GPA less than or equal to 4.0: ");
      String tempNum = input.next();
      num = checkGPA(checkDouble(tempNum));
    }
    return num;
  }

  static int checkCredits(int num) {
    /* overall credits should be greater than or equal to currentNoOfCredits - logically. */
    if (num < currentNoOfCredits) {
      System.out.print("Overall number of credits cannot be less than current class credits: ");
      String tempNum = input.next();
      num = checkCredits(checkInt(tempNum));
    }
    return num;
  }
  
  public static void main(String[] args) {
    System.out.println("GPA Calculator (up to 4.0)\n");
    getInput();
  }
}