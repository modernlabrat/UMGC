/*
 * Kyra Samuel
 * CMIS 242
 * Computer.java
 * 
 * Custom Computer obj: attributes => model, cpu, os, ram
 */
public class Computer {
  private String model, cpu, os, ram;
  
  public Computer() { }
  
  public Computer(String model, String cpu, String os, String ram) {
    this.model = model.trim();
    this.cpu = cpu.trim();
    this.os = os.trim();
    this.ram = ram.trim();
  }
  
  // getters
  String getModel() { 
    return model; 
  }

  String getCPU() { 
    return cpu; 
  }

  String getOS() { 
    return os; 
  }

  String getRAM() { 
    return ram; 
  }
  
   // setters
  
  void setModel(String newModel) {
    model = newModel;
  }
  
  void setCPU(String newCPU) {
    cpu = newCPU;
  }
  
  void setOS(String newOS) {
    os = newOS;
  }
  
  void setRAM(String newRAM) {
    ram = newRAM;
  }
  // method that prints the computer's info i.e attributes
  public String printComputer() {
    String computer = "\nInfo: Model => " + this.model + ", CPU => " + this.cpu + ", OS => " + this.os + ", RAM => " + this.ram + " GB";
    return computer;
  }
}