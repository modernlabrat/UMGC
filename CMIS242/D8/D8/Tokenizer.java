import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedHashMap;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

public class Tokenizer {
    private LinkedHashMap<Integer, String> tokens;
    private int tokenCount, colonCount;
    private BufferedReader bufferReader;

    public Tokenizer() {
        tokens = new LinkedHashMap<Integer, String>();

        tokenCount = 0;
        colonCount = 0;

        getFile();
    }

    private void getFile() {
        System.out.println("Select a File\n");
        try {
            int option = -1;

            JFileChooser fileChooser = new JFileChooser("."); // JFileChooser
            FileNameExtensionFilter filter = new FileNameExtensionFilter("TEXT FILES", "txt", "text");
            fileChooser.setFileFilter(filter); // set text files only filter

            while (option != JFileChooser.APPROVE_OPTION && option != JFileChooser.CANCEL_OPTION)
                option = fileChooser.showOpenDialog(null);

            if (option == JFileChooser.CANCEL_OPTION) {
                System.out.println("Canceled: No File Selected. Goodbye!");
                System.exit(0);
            }

            bufferReader = new BufferedReader(new FileReader(fileChooser.getSelectedFile()));
            tokenize();
        } catch (IOException ioe) {
            System.out.println("\tFailed. File Not Retrieved.");
        }
    }

    private void tokenize() {
        try {
            String line;
            int lineCount = 0;

            while ((line = bufferReader.readLine()) != null) {
                lineCount++; // increase lineCount
                line = line.trim(); // remove whitespace
                
                String[] preTokens = line.split(","); // split lines up by commas and words
                lineCount++; // increase lineCount

                for (String token : preTokens) {
                    token.trim();
                    tokenCount++;
                    tokens.put(tokenCount, token.trim());

                    String lastCharacter = token.substring(token.length() - 1);

                    if (lastCharacter.equals(":"))
                        colonCount++;
                }
            }
        } catch (IOException e) {
            System.out.println("Failed: Cannot Tokenize.");
            return;
        }
    }

    public LinkedHashMap<Integer, String> getTokens() {
        if (this.getColonCount() != 1 || this.getColonCount() == 0)
            this.tokens.clear();

        return this.tokens;
    }

    public int getColonCount() {
        return this.colonCount;
    }
}
