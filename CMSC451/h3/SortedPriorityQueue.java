package h3;
import java.util.*;
import java.io.*;

public class SortedPriorityQueue<T extends Comparable> {
  private ArrayList<T> queue;

  public SortedPriorityQueue() {
    queue = new ArrayList<T>();
  }

  public void add(T value) {
    // add to queue
    queue.add(value);
  }

  boolean isEmpty() {
    // check if queue is empty
    return queue.size() < 1;
  }

  T remove() {
    // remove 
    int index = 0;

    for (int i = 1; i < queue.size(); i++) 
      if (queue.get(index).compareTo(queue.get(i)) < 0) 
        index = i;

    T value = queue.get(index);

    if (index == queue.size() - 1)
      queue.remove(index);
    else
      queue.set(index, queue.remove(queue.size() - 1));
    
    return value;
  }

  T peek() {
    // get the peek value
    T value = queue.get(0);

    for (int i = 1; i < queue.size(); i++) 
      if (queue.get(i).compareTo(value) < 0) 
        value = queue.get(i);
    return value;
  }
}