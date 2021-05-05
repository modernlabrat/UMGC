/*
 * Kyra Samuel
 * CMIS 242
 * Discussion 2
 *
 * Program that creates Computer objects from user's input given the number of computers to create, adding encapsulation to the Computer obj.
 */

import java.io.BufferedReader; 
import java.io.IOException; 
import java.io.InputStreamReader;

import java.util.ArrayList;
class ComputerFactory {
  int count=0;
  
  ArrayList<Computer> computers = new ArrayList<Computer>();
  
  BufferedReader reader = new BufferedReader(new InputStreamReader(System.in)); 

  ComputerFactory() {
    System.out.print("Enter number of computers: ");
    try {
      String tempNum = reader.readLine();
      count = validInt(tempNum);
      createComputers();
    } catch (IOException e) {
      System.out.println("Error: " + e);
    }
  }

  void createComputers() throws IOException {
    // method that gets the computers info given number of computers i.e count;
    String model, cpu, os, ram;
    // promps user for computer info
    for(int x=1; x <= count; x++) {
      Computer computer = new Computer();
      System.out.printf("\nComputer %s:\n", x);
      System.out.printf("\tEnter the computer's model: ");
      model = reader.readLine();
      
      computer.setModel(model); // set model
      
      System.out.printf("\tEnter the computer's os: ");
      os = reader.readLine();
      computer.setOS(os);
      
      System.out.printf("\tEnter the computer's cpu model: ");
      cpu = reader.readLine();
      computer.setCPU(cpu);
      
      System.out.printf("\tEnter the computer's RAM in GB: ");
      String tempRAM = reader.readLine();
      ram = validRAM(tempRAM);
      computer.setRAM(ram);

      computers.add(computer); // add to computers ArrayList
    }
    
    printComputers(); // print all computers
  }
  
  void printComputers() {
    // method that prints all computers entered
    int x = 1;
    for (Computer computer: computers) {
      System.out.printf("\nComputer %s:\n\tmodel: %s\n\tos: %s\n\tcpu: %s\n\tram: %s", x, computer.getModel(), computer.getOS(), computer.getCPU(), computer.getRAM());
      x++;
    }
  }
  
  String validRAM(String ram) throws IOException {
    /* Simple check for input given a number, if its forCount sets the count value else sets the ram value*/
    try {
      int number = Integer.parseInt(ram);
      if (number > 0) 
        return ram;
      else 
        throw new Exception("Number less than 0.");
    } catch(Exception e) {
      // if not, ask for a new number
      System.out.print("\nPlease enter a whole number greater than 0.: ");
      String tempNum = reader.readLine();
      return validRAM(tempNum);
    }
  }
  
  Integer validInt(String num) throws IOException {
    try {
      int number = Integer.parseInt(num);
      if (number > 0) 
        return number;
      else 
        throw new Exception("Number less than 0.");
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