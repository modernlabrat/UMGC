/*
 * Kyra Samuel
 * CMIS 141
 * Week 7 Discussion
 * A program that populates and modifies an array based on user input. 
 *
 * The first term is the same in the modified array, then each subsequential term 
 * is the sum of the next term in the original array and previous result.
 */
import java.util.Arrays;
import java.util.Scanner;

class Main {
  static int capacity, index=0;
  static int numbers[];
  static Scanner input = new Scanner(System.in);

  static void modify() {
    for (int i = 0; i < numbers.length - 1; i++)
      numbers[i + 1] = numbers[i] + numbers[i + 1];
    }
  
  static void addToArray(int number) {
    // method to add to array
    numbers[index] = number;
    index++; // increase current index
  }

  static int checkInt(String num) {
    try {
      return Integer.parseInt(num);
    } catch(Exception e) {
      // if not, ask for a new number
      System.out.print("Please enter a whole number: ");
      String tempNum = input.next();
      return checkSize(tempNum); // check the number again and again and again!
    }
  }
  
  static int checkSize(String num) {
    try {
      int number = Integer.parseInt(num);
      if (number > 4)
        return number;
      else throw new Exception();
    } catch(Exception e) {
      // if not, ask for a new number
      System.out.print("Please enter a whole number greater than 4: ");
      String tempNum = input.next();
      return checkSize(tempNum); // check the number again and again and again!
    }
  }
  
  public static void main(String[] args) {
    System.out.print("How many numbers would you like to add to an Array?: ");
    String tempNum = input.next();
    capacity = checkSize(tempNum);
    
    numbers = new int[capacity]; // create an empty array
    
    for(int i=1; i <= capacity; i++) {
      System.out.printf("Number %s:\n\t", i);
      System.out.print("Enter an Integer: ");
      
      String tempInt = input.next();
      addToArray(checkInt(tempInt));
    }
    
    System.out.println("Array: ");
    for (int number: numbers) {
      System.out.print(number + " ");
    }

    modify();
  
    System.out.println("\nModified Array:");
    for (int number: numbers) {
      System.out.print(number + " ");
    }
  }
}