package P2;

import java.io.*;

import java.util.ArrayList;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

public class Main {
  public static void main(String[] args) {
    // set your path here
    String path = "C:\\Users\\kyras\\OneDrive\\Desktop\\Code\\UMGC\\CMSC451\\Project1\\Benchmarks";
    ArrayList<String[]> dataList = new ArrayList<>();

    FileNameExtensionFilter filter = new FileNameExtensionFilter("TEXT FILES", "txt", "text");
    JFileChooser fc = new JFileChooser(path);
    fc.setFileFilter(filter);

    if (fc.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
      // if file selected, parse the txt file
      File selectedFile = fc.getSelectedFile();
      try (BufferedReader br = new BufferedReader(new FileReader(selectedFile))) {
        String line;

        while((line = br.readLine()) != null) // store values into arrays
          dataList.add(line.split(" "));

        // create new benchmark frame
        new BenchmarkFrame(dataList, "Benchmark Report: " + selectedFile.getName());

      } catch (IOException e) {
        e.printStackTrace();
      }
    }
  }
  
}
