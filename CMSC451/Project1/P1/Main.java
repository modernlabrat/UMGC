package P1;
/*
 * Kyra Samuel
 * CMSC 495  
 * Main.java
 * 
 * Main Driver, JVM Warmups and creates Benchmark Reports
 */
public class Main {
  public static void main(String[] args) {
    int[] sizes = new int[]{100, 200, 300, 400, 500, 600, 700, 800, 900, 1000};

    // JVM warmup
    for (int i = 0; i < 200; i++) {
      long start = System.nanoTime();

      BenchmarkSorts warmupBenchmark = new BenchmarkSorts(sizes, i);
      warmupBenchmark.startSort();

      long end = System.nanoTime();
      long time = end - start;

      System.out.println("JVM Warmup => " + time + " nsec.");
    }

    for (int i = 1; i < 6; i++) {
      BenchmarkSorts benchmark = new BenchmarkSorts(sizes, i);
      benchmark.startSort();
      benchmark.createReports();
    }
  }
}