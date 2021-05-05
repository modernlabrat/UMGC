
/*
 * File: RunInventory.java
 * Author: Kyra Samuel
 * Class: CMIS 242 Assignment 1
 * Creation Date: March 28th, 2021
 * Description: A program that prompts the user for menu options to create an Inventory with Book objects.
 */

import java.util.Arrays;
import java.io.BufferedReader; 
import java.io.IOException; 
import java.io.InputStreamReader;

public class RunInventory {
  boolean run = true;

  Inventory bookInventory = new Inventory();
  BufferedReader reader = new BufferedReader(new InputStreamReader(System.in)); 

  RunInventory() {
    try {
      while(run) { start(); }
    } catch (IOException ioe) {
      System.out.println("IOError: " + ioe);
    }
  }

  void start() throws IOException {
    // method that prints menu and calls the correct function based on the user input
    System.out.println("\n    MENU    \n1: Add book\n2: Remove book\n3: Find book\n4: Display all books\n9: Exit program\n");
    System.out.print("Enter your selection: ");

    String selection = reader.readLine();
    String option = checkSelection(selection); // check if valid selection

    switch (option) {
      case "1":
        bookInventory.addBook();
        break;
      case "2":
        bookInventory.removeBook();
        break;
      case "3":
        bookInventory.findBook();
        break;
      case "4":
        bookInventory.displayBooks();
        break;
      case "9":
        reader.close();
        System.out.println("Thank you for using the program. Goodbye!");
        run = false;
        break;
    }
  }

  String checkSelection(String selection) throws IOException {
    // method that checks if the passed selection parameter is a valid menu option
    String[] menuOptions = {"1", "2", "3", "4", "9"};

    boolean contains = Arrays.stream(menuOptions).anyMatch(selection::equals);
    if (contains) { // if valid, return the selection
      return selection;
    } else { // prompt for new selection
      System.out.print("\nEnter a valid menu option: ");
      String newSelection = reader.readLine();

      return checkSelection(newSelection);
    }
  }

  public static void main(String[] args) {
    new RunInventory();
  }
}