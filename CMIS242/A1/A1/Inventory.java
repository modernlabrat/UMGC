/*
 * File: Inventory.java
 * Author: Kyra Samuel
 * Class: CMIS 242 Assignment 1
 * Creation Date: March 28th, 2021
 * Description: stores and manipulates Book Objects with addBook, removeBook, findBook, and displayBooks.
 */
import java.io.BufferedReader; 
import java.io.IOException; 
import java.io.InputStreamReader;
import java.util.LinkedHashMap;
import java.text.DecimalFormat;

public class Inventory {
  private LinkedHashMap<Integer, Book> books = new LinkedHashMap<Integer, Book>(); // stores Book objects
  BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
  DecimalFormat df = new DecimalFormat("###.00");

  void addBook() throws IOException {
    // method used to add Book objects to an LinkedHashMap
    System.out.print("Enter id: ");
    String sid = reader.readLine();

    int id = validId(sid); // check if id is valid

    System.out.print("Enter title: ");
    String title = reader.readLine();

    System.out.print("Enter price: ");
    String sPrice = reader.readLine();

    double price = checkPrice(sPrice);
    Book book = new Book(id, title, price);

    books.put(id, book);

    System.out.printf("\n%s was successfully added. Would you like to add another? Y/N: ", title);
    repeat("add"); // prompt for repeat
  }

  void removeBook() throws IOException {
    // method that removes Book objs from books HashLinkedMap
    if (books.size() > 0) { // check if there are any Books
      int id = promptForId(); // if so, get id from user
    
      if (books.containsKey(id)) { // if found, remove Book
        books.remove(id);
        System.out.printf("\nRemoved Book: %s. Would you like to remove another? Y/N: ", id);
        repeat("remove");
      } else {
        System.out.println("This book id is not listed in inventory.");
        System.out.print("\nWould you like to enter a new id? Y/N: ");
        repeat("remove"); // prompt for repeat
      }
    } else
      System.out.println("There are no books in inventory.");
  }

  void findBook() throws IOException {
    // method that finds a Book in the HashLinkedMap then display its information
    if (books.size() > 0) { // if there are Books
      int id = promptForId(); // then get id from user
      if (books.containsKey(id)) { // if id is found
        Book book = books.get(id); // get the Book from the books LinkedHashMap

        System.out.printf("\nid: %s\ntitle: %s\nprice: $%s\n", book.getId(), book.getTitle(), df.format(book.getPrice()));
        System.out.printf("\nWould you like to find another book? Y/N: ");
        repeat("find"); // prompt for repeat
      } else {
        System.out.println("This book is not in inventory.");
        System.out.print("\nWould you like to enter a new id? Y/N: ");
        repeat("find");
      }
    } else
      System.out.println("There are no books in inventory.");
  }

  void displayBooks() {
    // method that displays books
    if (books.size() > 0) { // if there are books
      books.forEach((key, value) -> { // loop through books
        System.out.printf("\nid: %s\n\ttitle: %s\n\tprice: $%s", value.getId(), value.getTitle(),
            df.format(value.getPrice()));
      });
    } else
      System.out.println("There are no books in inventory.");
  }
  
  private void repeat(String use) throws IOException {
    // method that handles repeating methods: addBook, removeBook, or findBook given user input
    String answer = reader.readLine();

    switch (answer) {
      case "y":
      case "Y":
        if (use == "add")
          addBook();
        else if (use == "remove")
          removeBook();
        else if (use == "find")
          findBook();
        break;
    }
  }
  
  private int promptForId() throws IOException {
    // method that checks for a valid integer value for id
    System.out.print("Enter id: ");
    String sid = reader.readLine();

    try {
      return Integer.parseInt(sid);
    } catch(NumberFormatException nfe) {
      System.out.print("Please enter a valid integer value greater than 0. ");
      return promptForId();
    }
  }

  private double checkPrice(String price) throws IOException {
    // method that checks for a valid price i.e optional decimal value with 2 places
    String currencyRegex = "(\\d+(\\.\\d{0,2})?)";

    try {
      if (price.matches(currencyRegex)) return Double.parseDouble(price);
      else
        throw new Exception();
    } catch (Exception e) {
      System.out.print("Invalid price. Enter a new price (double value): ");
      String tPrice = reader.readLine();
      return checkPrice(tPrice);
    }
  }

  private Integer validId(String id) throws IOException {
    // method that checks if the id passed is found in the books LinkedHashMap
    try {
      int number = Integer.parseInt(id);

      if (number > 0) {
        if (books.containsKey(number)) {
          System.out.println("This book is already listed in the inventory.");
          System.out.print("\nEnter new id: ");
          String tId = reader.readLine();
          return validId(tId);
        }
        else
          return number;
      }
      else {
        System.out.print("Invalid Input. Please enter a valid id greater than 0: ");
        String tId = reader.readLine();
        return validId(tId);
      }
    } catch(NumberFormatException nfe) {
      // if not, ask for a new number
      System.out.print("Please enter a valid integer value greater than 0: ");
      String tId = reader.readLine();
      return validId(tId);
    }
  }
}