/*
 * Kyra Samuel
 * CMSC 451
 * InsertionSorts.java
 * Description: Sorts an array iteratively and recursively 
 * 
 */
package P1;

public class InsertionSorts implements SortInterface {
  private int count;
  private long time;

  @Override
  public int getCount() {
    return count;
  }

  @Override
  public long getTime() {
    return time;
  }

  @Override
  public void iterativeSort(int[] currentArray) {
    // iterative insertion sort
    count = 0;
    time = 0;

    long start = System.nanoTime();

    int size = currentArray.length;

    for (int i=1; i < size; i++) {
      int current = currentArray[i];
      int next = i - 1;

      while (next >= 0 && currentArray[next] > current) {
        currentArray[next + 1] = currentArray[next];
        count++;
        next = next - 1;
      }

      currentArray[next + 1] = current;
    }

    long end = System.nanoTime();
    time = end - start;

    try {
      checkSort(currentArray);
    } catch (UnsortedException e) {
      System.out.println(e);
      System.exit(0);
    }
  }

  @Override
  public void recursiveSort(int[] currentArray) {
    // recursive insertion sort
    count = 0;
    time = 0;

    long start = System.nanoTime();

    currentArray = startRecursive(currentArray, currentArray.length);

    long end = System.nanoTime();
    time = end - start;

    try {
      checkSort(currentArray);
    } catch (UnsortedException e) {
      System.out.println(e);
      System.exit(0);
    }
  }

  public int[] startRecursive(int[] currentArray, int n) {
    if (n <= 1)
      return currentArray;

    if (n > 0) {
      startRecursive(currentArray, n-1);

      int current = currentArray[n-1];
      int next = n-2;
      
      while (next >= 0 && currentArray[next] > current) {
        currentArray[next+1] = currentArray[next];
        count++;
        next--;
      }

      currentArray[next+1] = current;
    }

    return currentArray;
  }

  public void checkSort(int[] array) throws UnsortedException {
    // check if array is sorted
    if (array.length == 0 || array.length == 1)
      return;
    
      for (int i = 1; i < array.length; i++) 
        if (array[i - 1] > array[i])
          throw new UnsortedException("Array is not sorted");
  }
}