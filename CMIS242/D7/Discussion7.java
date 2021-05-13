import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Discussion7 {
    BufferedReader reader;
    ArrayList<Integer> tokens;

    public Discussion7() throws IOException, DiscussionError {
        reader = new BufferedReader(new InputStreamReader(System.in));
        tokens = new ArrayList<Integer>();
        input();
        add();
    }


    protected void input() throws IOException, DiscussionError {
        System.out.print("Enter Data: ");

        String response = reader.readLine().trim();
        String[] preTokens = response.split(","); // split lines up by commas

        if (preTokens.length == 6) {
            for (String preToken: preTokens) {
                try {
                    int token = Integer.parseInt(preToken);
                    tokens.add(token);
                } catch (NumberFormatException nfe) {
                    throw new DiscussionError("Data does not contain all int value type.");
                }
            }
        } else
            throw new DiscussionError("Invalid Data Count. Please enter 6 digits separated by a comma.");
    }

    protected void add() {
        double result = 0;

        int currentIndex = 0;    
        for (int i = 0; i < 6; i++) {
            int lastToken = tokens.get(currentIndex);
            result = result + lastToken;
            currentIndex++;
        }

        System.out.println("Result: " + String.valueOf(result));
    }
}