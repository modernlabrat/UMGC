/*
 * Author: Kyra Samuel
 * File: Main.java
 * Description: Creates a new MediaRentalSystem object
 */
import java.io.IOException;
public class Main {
    public static void main(String[] args) {
        try {
            new MediaRentalSystem();
        } catch(IOException ioe) {
            System.out.println(ioe.getMessage());
            System.exit(0);
        } catch (NullPointerException npe) {
            System.out.println("Goodbye! ");
            System.exit(0);
        }
    }
}