
/*   
 * File: EmployeesFactory.java
 * Author: Kyra Samuel
 * Class: CMIS 141 – Project 3
 * Creation Date:  April 14, 2021 
 * Description:  A java class that prompts the user for menu options to add, display, search Employee objects.
 */
import java.io.BufferedReader;
import java.util.*;
import java.io.IOException;
import java.io.InputStreamReader;

public class EmployeesFactory {
    private HashMap<Integer, Employee> employees;
    private BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
    private boolean run = true;

    public EmployeesFactory() {
        employees = new HashMap<Integer, Employee>();

        try {
            while (run) 
                start();
        } catch (IOException ioe) {
            System.out.println("Error with EmployeesFactory");
        }
     }

     void loadEmployees() throws IOException {
        // a method that loads a number of employees based on user input
        if (employees.size() > 0) {
            System.out.print("Enter the number of employees to be loaded: ");
            String response = input.readLine();

            int loadNumber = checkInt(response);

            if (employees.size() < loadNumber) {
                System.out.printf("There are only %s employees in the database, displaying all %s employees.\n",
                        employees.size(), employees.size());

                for (int i = 0; i < employees.size(); i++)
                    printEmployee(i);
            } else {
                System.out.printf("Displaying %s out of $s employees:\n", loadNumber, employees.size());
                for (int i = 0; i <= loadNumber; i++) {
                    System.out.println(i + 1);
                    printEmployee(i);
                }
            }
        } else 
            System.out.println("There are no employees stored in the database.");
    }
    
    void addEmployee() throws IOException {
        // a method that adds Employee objects to a HashMap
        System.out.print("Enter employee id: ");
        String tId = input.readLine();
        String id = checkId(tId);

        System.out.print("Enter employee's first name: ");
        String tFirstName = input.readLine();
        String firstName = checkName(tFirstName);

        System.out.print("Enter employee's last name: ");
        String tLastName = input.readLine();
        String lastName = checkName(tLastName);

        String name = firstName + " " + lastName;

        System.out.print("Enter employee's annual salary without commas: ");
        String tSalary = input.readLine();
        double salary = checkSalary(tSalary);

        int size = employees.size();

        Employee employee = new Employee(name, id, salary, size);

        employees.put(size, employee);

        System.out.printf("\nSuccessfully added employee: %s", name);
    }

    void displayEmployees() {
        // a method that displays all employees
        if (employees.size() > 0) {
            System.out.println("Displaying all Employees: \n");
            for (int i = 0; i < employees.size(); i++) 
                printEmployee(i);
        } else
            System.out.println("There are no employees to display.");
    }

    void retrieveEmployee() throws IOException {
        // a method that retrieves an employee based on the Id given by the user.
        System.out.print("Enter employee's id: ");
        String id = input.readLine().trim();
        int employeesKey;
        boolean found = false;

        for (Employee employee : employees.values()) {

            String currentId = employee.getId();

            if (currentId.equals(id)) {
                found = true;
                employeesKey = employee.getKey();
                printEmployee(employeesKey);
            }
        }

        if (!found)
            System.out.printf("\nCould not find an employee with the id: %s", id);
    }

    void retrieveSalaries() throws IOException {
        // a method that displays Employee objects between the Salary range provided by the user.
        ArrayList<Employee> employeesInRange = new ArrayList<Employee>();

        System.out.print("Enter the first salary: ");
        String tFSalary = input.readLine();
        double fSalary = checkSalary(tFSalary);

        System.out.print("Enter the second salary: ");
        String tSSalary = input.readLine();
        double sSalary = checkSalary(tSSalary);

        
        for (Employee employee : employees.values()) {
            Double currentSalary = employee.getSalary();
            if (currentSalary >= fSalary && currentSalary <= sSalary)
                employeesInRange.add(employee);
        }
        
        if (employeesInRange.size() > 0) {
            for (Employee employee : employeesInRange) {
                int employeesKey = employee.getKey();
                printEmployee(employeesKey);
            }
        } else
            System.out.printf("\nThere are no employees within the salary range (%s - %s)", fSalary, sSalary);
    }

    void start() throws IOException {
        // a method that displays a menu and prompts the user for an option then calls the appropriate function
        String options[] = { "1", "2", "3", "4", "5", "6" };

        System.out.println(
                "\n      MENU      \n1: Load employee's data\n2: Add new employees\n3: Display all new employees\n4: Retrieve specific employee's data\n5: Retrieve employees with salaries based on range\n6: Exit\n");
        System.out.print("Choose an option: ");
        String option = input.readLine();

        Boolean contains = Arrays.asList(options).contains(option.trim());

        if (contains) {
            switch (option) {
            case "1":
                loadEmployees();
                break;
            case "2":
                addEmployee();
                break;
            case "3":
                displayEmployees();
                break;
            case "4": 
                retrieveEmployee();
                break;
            case "5":
                retrieveSalaries();
                break;
            case "6":
                System.out.println("Thanks for participating! Goodbye.");
                run = false;
                System.exit(0);
            }
        } else {
            System.out.println("Enter a valid menu option.");
            start();
        }
    }

    String checkName(String response) throws IOException {
        // a method that checks if the name is alpha only
        if (isAlpha(response))
            return response;
        else {
            System.out.println("Please enter letters only without spaces or numbers: ");
            String tName = input.readLine();
            return checkName(tName.trim());
        }
    }

    public boolean isAlpha(String name) {
        // a method that checks all the characters in a String to determine its validity (isAlpha)
        char[] chars = name.toCharArray();
        for (char c : chars) {
            if (!Character.isLetter(c)) {
                return false;
            }
        }
        return true;
    }

    double checkSalary(String salaryString) throws IOException {
        // a method that checks for a valid Salary input greater than 0
        try {
            double salary = Double.parseDouble(salaryString);

            if (salary > 0)
                return salary;
            else
                throw new Exception();
        } catch (Exception e) {
            System.out.print("Please enter a valid double value greater than 0 for the salary: ");
            String response = input.readLine();
            return checkSalary(response.trim());
        }
    }
    
    String checkId(String id) throws IOException {
        // a method that checks if the requested id is already in the EmployeeFactory
        boolean valid = true;
    
        for (Employee employee : employees.values()) {

            String currentId = employee.getId();

            if (currentId.equals(id))
                valid = false;
        }

        if (valid)
            return id;
        else {
            System.out.print("This id is already entered into the Employee Database. Please enter a new id: ");
            String tId = input.readLine().trim();
            return checkId(tId);
        }
    }

    int checkInt(String intString) throws IOException {
        // a method that checks for a valid int value
        try {
            return Integer.parseInt(intString);
        } catch (Exception e) {
            System.out.print("Please enter a valid int: ");
            String response = input.readLine();
            return checkInt(response.trim());
        }
    }

    void printEmployee(int index) {
        // a method that prints the Employee obj using the index of employees HashMap
        Employee currentEmployee = employees.get(index);
        
        System.out.printf("\n\nEmployee Id: %s \nName: %s \nSalary %s", currentEmployee.getId(), currentEmployee.getName(), currentEmployee.getSalary());
    }
}