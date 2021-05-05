/*   
 * File: Main.java
 * Author: Kyra Samuel
 * Class: CMIS 141 – Project 3
 * Creation Date:  April 14, 2021 
 * Description:  A java object called Employee with 4 attributes: name, id, salary, key.
 */
public class Employee {
    private String name, id;
    private Double salary;
    private int key;

    public Employee(String name, String id, Double salary, int key) {
        this.name = name;
        this.id = id;
        this.salary = salary;
        this.key = key;
    }

    String getName() {
        return this.name;
    }

    String getId() {
        return this.id;
    }

    Double getSalary() {
        return this.salary;
    }

    int getKey() {
        return this.key;
    }

    void setKey(int newKey) {
        this.key = newKey;
    }
}
