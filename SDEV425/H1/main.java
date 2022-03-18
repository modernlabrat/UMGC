import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Main {
  public static void main(String[] args) {
    // Read the filename from the command line argument
    String filename = args[0];
    BufferedReader inputStream = null;

    String fileLine;
    try {
      inputStream = new BufferedReader(new FileReader(filename));

      System.out.println("Email Addresses:");
      // Read one Line using BufferedReader
      while ((fileLine = inputStream.readLine()) != null) {
          System.out.println(fileLine);
      }
    } catch (IOException io) {
        System.out.println("File IO exception" + io.getMessage());
    } finally {
      // Need another catch for closing 
      // the streams          
      try {
          if (inputStream != null) {
              inputStream.close();
          }
      } catch (IOException io) {
          System.out.println("Issue closing the Files" + io.getMessage());
      }

    }
  }
}
