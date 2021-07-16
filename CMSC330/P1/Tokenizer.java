import java.util.*;
import java.io.*;

public class Tokenizer {
    private BufferedReader bufferedReader;
    private int tokenCount;
    private int lineCount;
    private LinkedHashMap<Integer, String> tokens;

    public Tokenizer(BufferedReader bufferedReader) {
        tokens = new LinkedHashMap<Integer, String>();
        tokenCount = 0;
        lineCount = 0;

        this.bufferedReader = bufferedReader;
    }

    // Tokenize Input - adds tokens into the "tokens" ArrayList
    protected void tokenize() {
        System.out.println("\nTest (2/3): Tokenize");

        try {
            String line, text = "";
            boolean isString = false;

            while ((line = bufferedReader.readLine()) != null) {
                lineCount++; // increase lineCount
                line = line.trim(); // remove whitespace

                String[] preTokens = line.split("(?=[.:;(),\"])|(?<=[.:;(),\"])|\\s"); // split lines up by characters

                lineCount++; // increase lineCount

                for (String token : preTokens) {
                    if (isString) {

                        if (token.equals("\"")) {
                            tokenCount++;
                            tokens.put(tokenCount, text.trim());

                            text = "";

                            tokenCount++;
                            tokens.put(tokenCount, token.trim());
                        } else
                            text += token + " ";
                    } else if (token.trim().length() > 0) {
                        tokenCount++;
                        tokens.put(tokenCount, token.trim());
                    }

                    if (token.equals("\""))
                        isString = !isString;
                }
            }

        } catch (IOException e) {
            System.out.println("\tFailed: Tokenize Incomplete - " + e.getMessage());
            System.exit(0);
        }

        System.out.println("\n\tSuccess: Tokenized.");
    }

    protected LinkedHashMap<Integer, String> getTokens() {
        return this.tokens;
    }
}
