/*
 * Discussion 8
 * Kyra Samuel
 * CMIS 141
 *
 *
 * Program that creates Computer objects from user's input given the number of computers to create.
 */

import java.io.BufferedReader; 
import java.io.IOException; 
import java.io.InputStreamReader;

import java.util.ArrayList;
class ComputerFactory {
  static boolean run = true;
  
  static ArrayList<Computer> computers = new ArrayList<Computer>();
  
  static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in)); 
  
  ComputerFactory() {
    System.out.println("Enter the computer's info below.\n");
    try {
      Computer computer = createComputer();
      System.out.println("Compuer " + computer.printComputer());
      getUpdate(computer);
    } catch (IOException e) {
      System.out.println("Error: cannot crrate computer. " + e);
    }
  }

  static Computer createComputer() throws IOException {
    // method that gets the computers info given number of computers i.e count;
    String model, cpu, os, ram;
    // promps user for computer info
    System.out.printf("\tEnter the computer's model: ");
    model = reader.readLine();
      
    System.out.printf("\n\tEnter the computer's os: ");
    os = reader.readLine();
      
    System.out.printf("\n\tEnter the computer's cpu model: ");
    cpu = reader.readLine();
      
    System.out.printf("\n\tEnter the computer's RAM in GB: ");
      String tempRAM = reader.readLine();
    ram = validInt(tempRAM);

    Computer computer = new Computer(model, cpu, os, ram); // create new computer obj
     return computer;
    }
  
  static void getUpdate(Computer computer) throws IOException {
    while(run) {
      System.out.print("\n\nWould you like to update the: \n\ta) os\n\tb) ram\n\tc) cpu\n\td) model\n\te) exit program\nEnter a Choice: ");
      String updateReq = reader.readLine();
      
      switch(updateReq) {
        case "a":
        case "A":
          System.out.print("Enter new os: ");
          String newOS = reader.readLine();
          computer.update("os", newOS);
          break;
        case "b":
        case "B":
          System.out.print("Enter new ram: ");
          String tempRam = reader.readLine();
          String newRAM = validInt(tempRam);
          
          computer.update("ram",newRAM);
          break;
        case "c":
        case "C":
          System.out.print("Enter a new cpu: ");
          String newCPU = reader.readLine();
          computer.update("cpu", newCPU);
          break;
        case "d":
        case "D":
          System.out.print("Enter a new model: ");
          String newModel = reader.readLine();
          computer.update("model", newModel);
          break;
        case "e":
        case "E":
          System.out.println("Thanks for participating! Goodbye.");
          reader.close();
          run = false;
          break;
        default:
          System.out.println("Please enter a valid menu option!");
          getUpdate(computer);
          break;
      }
      
      System.out.println("New Update: Computer " + computer.printComputer());
    }
  }
  static String validInt(String num) throws IOException {
    /* Simple check for input given a number, if its forCount sets the count value else sets the ram value*/
    try {
      int number = Integer.parseInt(num);
      return num;
    } catch(Exception e) {
      // if not, ask for a new number
      System.out.print("\nPlease enter a whole number greater than 0.: ");
      String tempNum = reader.readLine();
      return validInt(tempNum);
    }
  }
  
  public static void main(String[] args) {
    new ComputerFactory();
  }
}