/*
 * Kyra Samuel
 * CMIS 242
 * Computer.java
 * 
 * Custom Computer obj: attributes => model, cpu, os, ram
 */
public class Computer {
  private String model, cpu, os, ram;
  
  public Computer(String model, String cpu, String os, String ram) {
    this.model = model.trim();
    this.cpu = cpu.trim();
    this.os = os.trim();
    this.ram = ram.trim();
  }
  
  // method that prints the computer's info i.e attributes
  public String printComputer() {
    String computer = "\nInfo: Model => " + this.model + ", CPU => " + this.cpu + ", OS => " + this.os + ", RAM => " + this.ram + " GB";
    return computer;
  }
  
  public void update(String updateReq, String updateValue) {
    switch(updateReq) {
      case "os":
        this.os = updateValue;
        break;
      case "cpu":
        this.cpu = updateValue;
        break;
      case "model":
        this.model = updateValue;
        break;
      case "ram":
        this.ram = updateValue;
    }
  }
}