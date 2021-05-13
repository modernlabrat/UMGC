import java.io.IOException;

public class Main {
    private static void run() {
        try {
            new Discussion7();
        } catch (DiscussionError de) {
            System.out.println(de.getMessage());
            run();
        } catch (IOException ioe) {
            System.out.println(ioe.getMessage());
        }
    }

    public static void main(String[] args) throws IOException, DiscussionError {
        run();
    }
}
