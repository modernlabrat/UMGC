import java.io.*;
import java.util.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.File; 
import javax.swing.JFileChooser; 
import javax.swing.filechooser.FileSystemView; 



public class Main {
 
    public static void main(String[] args) { 


      JFileChooser jfc = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory()); 
        
      int returnValue = jfc.showOpenDialog(null);

      if (returnValue == JFileChooser.APPROVE_OPTION) {
        //get the file
        File selectedFile = jfc.getSelectedFile(); 
        //read the Weight file
        try {
          BufferedReader br = new BufferedReader(new FileReader(selectedFile));
          String row;
          int rowCounter = 0;
          while((row = br.readline()) != null) {
            //keep track of row counter
            if (rowCounter >= 25) {
              //display error and exit
              Sytstem.out.println("The Weight file cannot have more than 25 lines\n\nThe program is now closing");
              Sytem.exit(0);
            } 
            //start to read the lines
            String[] parts = row.split(",");
          } // end line reading loop
        } catch (Exception e) {
          //print out file exception
          System.out.println(e.toString());
        }
      }                

            
            // BufferedReader br = new BufferedReader(new FileReader(selectedFile));
            
            // //while what is true? there is no variable that ever sets itself to false so this loop should run endlessly
            // //essentially what you want this to do is run as long as there is data being read / available
			// while(true) {
            //     //here you have an IOexception that you need to handle, you can probably group the two exceptions together
			// 	String line = br.readLine();
			// 	while (line != null) {
			// 		String[] parts = line.split(",");
			// 	}
			// }
        }
        
    
    } 
    

    private static int findMinimum(Weight[] weights) {
        //here you are trying to convert a weight object to an int, you have to produce some number
        //a good way to do that is to take a single number, instead of pounds and ounces, perhaps converting weight[0] to ounces
        //and using that number as the max value, then comparing other weights (in ounces) to that.
        int maxValue = weights[0];
        
		 for(int i=1;i < weights.length;i++){
          if(weights[i] > maxValue){
	          maxValue = weights[i];
	       }
        }
          return maxValue;
    } 
 
 
 
    private static int findMaximum(Weight weights[]) {

        //same problem as the find MInimum
        int minValue = weights[0];
        
		for(int i=1;i<weights.length;i++){
           if(weights[i] < minValue){
	           minValue = weights[i];
	       }
       }
       return minValue;
    } 
 
 
 
    private static double findAverage(Weight weights[]) {

        //so we never told the computer how to work with the + operator and our weights class. we could, but that is complicated and we don't need to
        //so, really we have similar problems here that you would encounter in the other methods.
        //you need to use the .addTo methods, and the .divide methods to solve this problem. 
        int sum =0;
		 for (int i=0; i<weights.length; i++) {
             sum += weights[i]; 
        }
		 double average = sum/weights.length;
        return average; 
    
    } 
 

}



