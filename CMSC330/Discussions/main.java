class Main {
  int id = 0;
  String name = "Kyra";

  static void printEmployee() {
    int age = 22;
    String name = "Vishnu";

    System.out.println("ID: " + id);
    System.out.println("Name: " + name);      
    System.out.println("Name: " + this.name); 
    System.out.println("age: "+ this.age);
  }

  public static void main(String args[]) {
    printEmployee();
  }
}