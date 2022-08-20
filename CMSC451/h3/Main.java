package h3;

import java.util.*;
import java.io.*;

public class Main {
  public static void main(String a[]) {
    SortedPriorityQueue<Integer> queue = new SortedPriorityQueue<>();
    
    queue.add(1);  
    queue.add(2);                           
    queue.add(3);                           
    queue.add(4);                  
    
    System.out.println("Peek => " + queue.peek());
    System.out.println("Remove => " + queue.remove());
    System.out.println("Peek => " + queue.peek());
  }
}