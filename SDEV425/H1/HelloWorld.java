import java.util.Date;
import java.util.Scanner;

public class HelloWorld {
  static Scanner sc = new Scanner(System.in);

  static void multiplyTwo() {
    System.out.print("Enter a number: ");

    try {
      int x = sc.nextInt();
      int result = x*2;

      System.out.println("Result: " + String.valueOf(result));
    } catch(Error e) {
      System.out.println("Please enter a valid number.");
      multiplyTwo();
    }
  }
  /*public static void main(String[] args) {
    Date now = new Date();
    System.out.println("Hello World, it is " + now.toString());
    multiplyTwo();
  };*/
};