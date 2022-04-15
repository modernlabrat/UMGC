/*
 * Author: Kyra Samuel
 * Project: Homework 1
 * Class: SDEV425_1.java
 * Instructor: Craig Poma
 * Created On: 04/14/2022
 */
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.*;

public class SDEV425_1 {
  /**
  * @param args the command line arguments
  */

  static HashMap<String, String> usernames = new HashMap<String, String>();
  static Scanner sc = new Scanner(System.in); 

  public static boolean checkPassword(String enteredPassword, Logger logger) {
    if (enteredPassword.equals("Adm1n$")) return true;
    else {
      System.out.println("Invalid Password");
      return isAuthenticated(logger);
    }
  }

  public static boolean isAuthenticated(Logger logger) {
    // check if the user is an authorized user
    System.out.print("Username: ");
    String enteredUsername = sc.nextLine().trim();

    System.out.print("Password: ");
    String enteredPassword = sc.nextLine().trim();

    switch(enteredUsername) { // simple switch statement to check usernames
      case "ksamuel":
      case "cpoma":
        return checkPassword(enteredPassword, logger);
      default:
        String logMessage = "Unauthorized access attempt => username: " + enteredUsername + " file: EmailAddresses.txt";
        logger.log(Level.WARNING, logMessage);
        break;
    }

    return false;
  }

  public static void printEmails(BufferedReader inputStream, String filename, Logger logger) {
    // checks username and password then prints EmailAddresses.txt
    String fileLine;

    try {
      inputStream = new BufferedReader(new FileReader(filename));

      if (isAuthenticated(logger)) {
        System.out.println("Email Addresses:");
        // Read one Line using BufferedReader
        while ((fileLine = inputStream.readLine()) != null)
          System.out.println(fileLine);
      }
    } catch (IOException io) {
      System.out.println("File IO exception" + io.getMessage());
    } finally {
      // Need another catch for closing the streams
      try {
        if (inputStream != null) {
          inputStream.close();
        }
      } catch (IOException io) {
        System.out.println("Issue closing the Files" + io.getMessage());
      }
    }
  }

  public static void main(String[] args) {
    // Read the filename from the command line argument
    usernames.put("ksamuel", "admin");
    usernames.put("cpoma", "admin");
    usernames.put("jdoe", "member");
    usernames.put("cjames", "member");


    String filename = args[0];
    BufferedReader inputStream = null;

    AuditLogger auditLogger = new AuditLogger();
    Logger logger = auditLogger.startLogger();

    printEmails(inputStream, filename, logger);
    sc.close();

    // HelloWorld Part 1.

    HelloWorld hello = new HelloWorld();
    hello.run();
  }
}