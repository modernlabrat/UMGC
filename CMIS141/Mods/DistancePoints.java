/* Author: Kenneth Paul
 * Modified By: Kyra Samuel
 * Description: Distance Between Two Points
 */

import java.util.Scanner;

public class DistancePoints{
  public static void main (String[] args)
   {

    Scanner input = new Scanner(System.in);
  // Prompts the user for the points
     
     System.out.print("Enter x-point #1: ");
     double x1 = input.nextDouble(); //lets say we input 5 here
     System.out.print("Enter y-point #1: ");
     double y1 = input.nextDouble(); //we input 10 here
     System.out.print("Enter x-point #2: ");
     double x2 = input.nextDouble(); //15 here
     System.out.print("Enter y-point #2: ");
     double y2 = input.nextDouble(); //20 here

     double d = distance(x1, y1, x2, y2); //we get to this line. It takes us to line 31------We are back here. Because we put return dist on line 36, we will store calculated value to distance
     double s = slope(x1, y1, x2, y2);

     System.out.printf("The distance of both points is %s\n", d); //we just print the result here
     System.out.printf("The slope: %s", s);
    }
     
    public static double distance(double a1, double b1, double a2, double b2){ 
      double dist = Math.sqrt(Math.pow((a1-a2), 2) + Math.pow((b1-b2), 2));  
      return dist; 
    }

    static double slope(double a1, double b1, double a2, double b2) {
      double result = (b2 - b1) / (a2 - a1);
      return result;
    }
  }