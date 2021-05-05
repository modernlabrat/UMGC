/*
 * File: Book.java
 * Author: Kyra Samuel
 * Class: CMIS 242 Assignment 1
 * Creation Date: March 28th, 2021
 * Description: Custom Book Obj with attributes: id, title, and price.
 */
public class Book {
  private int id;
  private String title;
  private double price;

  Book(int id, String title, double price) {
    this.id = id;
    this.title = title;
    this.price = price;
  }

  // getter methods
  int getId() { return id; }
  String getTitle() { return title; }
  double getPrice() { return price; }
}