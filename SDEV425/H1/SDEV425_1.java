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
import java.util.regex.*;
import java.util.*;

public class SDEV425_1 {
  /**
  * @param args the command line arguments
  */

  static HashMap<String, List<String>> db;
  static Scanner sc; 
  static CodeWindow codeWindow;
  static Audits audits;

  
  /** 
   * Checks if the password is correct for the user 
   * Also conducts multi-factor identification
   * 
   * @param enteredPassword - the password the user entered
   * @param user - the username the user entered
   * @return boolean - true/false if the password is verified
   */
  public static boolean checkPassword(String enteredPassword, String user) {
    codeWindow.showWindow();

    if (enteredPassword.equals("Adm1n$")) {
      System.out.print("Enter code: ");
      String enteredCode = sc.nextLine().trim();

      if (enteredCode.equals(db.get(user).get(1))) {
        codeWindow.hideWindow();
        return true;
      } else {
        int attempts = Integer.parseInt(db.get(user).get(2));
        attempts++;

        String logMessage = "Failed multi-auth attempt => username: " + user + " Attempt: " + attempts;

        audits.addAudit(logMessage);
        System.out.println("Invalid Code, Try Again");

        db.get(user).set(2, String.valueOf(attempts));

        return checkPassword(enteredPassword, user);
      }
    } else {
      System.out.println("Invalid Password");
      return isAuthenticated();
    }
  }

  
  /** 
   * Checks if the user is authenticated
   * @return boolean 
   */
  public static boolean isAuthenticated() {
    // check if the user is an authorized user
    System.out.print("Username: ");
    String enteredUsername = sc.nextLine().trim();

    System.out.print("Password: ");
    String enteredPassword = sc.nextLine().trim();

    switch(enteredUsername) { // simple switch statement to check usernames
      case "ksamuel":
      case "cpoma":
        return checkPassword(enteredPassword, enteredUsername);
      default:
        String logMessage = "Unauthorized access attempt => username: " + enteredUsername + " file: EmailAddresses.txt";
        audits.addAudit(logMessage);
        break;
    }

    return false;
  }

  public static boolean validEmail(String email) {
    String regex = "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@" 
    + "[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$";
    Pattern pattern = Pattern.compile(regex);
    Matcher matcher = pattern.matcher(email);

    return matcher.matches();
  }
  
  /** 
   * Authenticates then prints the email addresses in the emailAddress.txt
   * @param inputStream - input stream from  file
   * @param filename - name of file to open
   */
  public static void printEmails(BufferedReader inputStream, String filename) {
    // checks username and password then prints EmailAddresses.txt
    String fileLine;
    String[] ext = filename.split("\\."); // get file extension
  
    if (ext[1].equals("txt")) {
      try {
        inputStream = new BufferedReader(new FileReader(filename));

        if (isAuthenticated()) {
          System.out.println("Email Addresses:");
          // Read one Line using BufferedReader
          while ((fileLine = inputStream.readLine()) != null) {
            if (validEmail(fileLine.trim()))
              System.out.println(fileLine);
            else 
              System.out.println("Invalid Email Found");
          }
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
    else {
      System.out.println("Only txt files permitted");
      System.exit(1);
    }
  }

  
  /** 
   * Prompts for user/password and prints content from args file
   * @param args
   */
  public static void main(String[] args) {
    // Read the filename from the command line argument
    db = new HashMap<>();
    audits = new Audits();
    sc = new Scanner(System.in); 
    codeWindow = new CodeWindow();


    codeWindow.hideWindow();
    int authCode = codeWindow.getAuthCode();

    db.put("ksamuel", Arrays.asList("admin", String.valueOf(authCode), "0"));
    db.put("cpoma", Arrays.asList("admin", String.valueOf(authCode), "0"));
    db.put("jdoe", Arrays.asList("member", String.valueOf(authCode), "0"));
    db.put("cjames", Arrays.asList("member", String.valueOf(authCode), "0"));


    String filename = args[0];
    BufferedReader inputStream = null;

    printEmails(inputStream, filename);
    sc.close();

    // HelloWorld Part 1.

    HelloWorld hello = new HelloWorld();
    hello.run();
  }
}