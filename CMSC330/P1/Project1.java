import java.util.*;
import java.io.*;
import javax.swing.*;
import javax.swing.filechooser.*;

/*
 * Kyra Samuel
 * CMSC330
 * Project1.java
 * Project1 retrieves a file, tokenizes the file, 
 * then builds a GUI if the syntax is correct
 * based on the grammar defined in Project1.pdf
 */

public class Project1 {
  private LinkedHashMap<Integer, String> tokens;

  // Constructor
  public Project1() {
    tokens = new LinkedHashMap<Integer, String>();

    run();
  }

  private void run() {
    System.out.println("\nTest (1/3): Retrieve File");
    
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

      BufferedReader bufferedReader = new BufferedReader(new FileReader(fileChooser.getSelectedFile()));

      System.out.println("\tSuccess: Retrieved File");

      Tokenizer tokenizer = new Tokenizer(bufferedReader);
      tokenizer.tokenize();
      tokens = tokenizer.getTokens();

      Parser parser = new Parser(tokens);

      parser.parseGUI();
    } catch (IOException ioe) {
      System.out.println("\tFailed. File Not Retrieved.");
      System.exit(0);
    }
  }
}