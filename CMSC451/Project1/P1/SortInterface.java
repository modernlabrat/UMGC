package P1;
/*
 * Kyra Samuel
 * CMSC 451
 * SlotInterface.java
 */

public interface SortInterface {
  void recursiveSort(int[] list) throws UnsortedException;
  void iterativeSort(int[] list) throws UnsortedException;
  int getCount();
  long getTime();
}
