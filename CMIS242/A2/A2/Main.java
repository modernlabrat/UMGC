import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        try {
            new OrderSystem();
        } catch(IOException e) {
            System.out.println("Error receiving input.");
        }
    }
}
