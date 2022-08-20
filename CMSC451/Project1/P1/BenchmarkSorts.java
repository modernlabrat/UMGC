package P1;
/*
 * Kyra Samuel
 * CMSC 451
 * BenchmarkSorts.java
 * 
 * References:
 *** Iterative - https://www.geeksforgeeks.org/insertion-sort/ 
 *** Recursive - https://www.geeksforgeeks.org/recursive-insertion-sort/ 
 */

import java.io.*;
import java.util.*;

public class BenchmarkSorts {
  private final int MAX_RUNS = 50;
  private InsertionSorts insertionSort;

  private int benchmarkCount;

  private int[] data;
  private double[] iterativeCounts;
  private double[] iterativeAvgCounts;
  private double[] iterativeCoefCounts;

  private double[] recursiveCounts;
  private double[] recursiveAvgCounts;
  private double[] recursiveCoefCounts;


  private double[] iterativeTimes;
  private double[] iterativeAvgTimes;
  private double[] iterativeCoefTimes;

  private double[] recursiveTimes;
  private double[] recursiveAvgTimes;
  private double[] recursiveCoefTimes;


  Random rand = new Random();

  public BenchmarkSorts(int[] data, int benchmarkCount) {
    this.insertionSort = new InsertionSorts();
    this.data = data;

    this.iterativeCounts = new double[MAX_RUNS];
    this.iterativeTimes = new double[MAX_RUNS];
    this.iterativeAvgCounts = new double[data.length];
    this.iterativeAvgTimes = new double[data.length];
    this.iterativeCoefCounts = new double[data.length];
    this.iterativeCoefTimes = new double[data.length];
    
    this.recursiveCounts = new double[MAX_RUNS];
    this.recursiveTimes = new double[MAX_RUNS];
    this.recursiveAvgCounts = new double[data.length];
    this.recursiveAvgTimes = new double[data.length];
    this.recursiveCoefCounts = new double[data.length];
    this.recursiveCoefTimes = new double[data.length];

    this.benchmarkCount = benchmarkCount;
  }

  public void startSort() {
    for (int i = 0; i < data.length; i++) {
      // for every n, create and sort fifty arrays using insertion sort, where n = data.length
      int[] iterativeArray = new int[data[i]];
      int[] recursiveArray = new int[data[i]];

      for (int j = 0; j < MAX_RUNS; j++) {
        // generate random values between 1 and 2000 then fill array
        for (int k=0; k < data[i]; k++) {
          int value = rand.nextInt((2000 - 1) + 1) + 1;

          iterativeArray[k] = value;
          recursiveArray[k] = value;
        }

        // start iterative sort
        insertionSort.iterativeSort(iterativeArray);
        iterativeCounts[j] = insertionSort.getCount();
        iterativeTimes[j] = insertionSort.getTime();

        // start recursive sort
        insertionSort.recursiveSort(recursiveArray);
        recursiveCounts[j] = insertionSort.getCount();
        recursiveTimes[j] = insertionSort.getTime();
      }



      // calculate the average count and time
      iterativeAvgCounts[i] = calculateAverage(iterativeCounts);
      iterativeAvgTimes[i] = calculateAverage(iterativeTimes);
      iterativeCoefCounts[i] = calculateCoefficientOfVariance(iterativeCounts);
      iterativeCoefTimes[i] = calculateCoefficientOfVariance(iterativeTimes);

      recursiveAvgCounts[i] = calculateAverage(recursiveCounts);
      recursiveAvgTimes[i] = calculateAverage(recursiveTimes);
      recursiveCoefCounts[i] = calculateCoefficientOfVariance(recursiveCounts);
      recursiveCoefTimes[i] = calculateCoefficientOfVariance(recursiveTimes);
    }
  }

  public double calculateAverage(double[] data) {
    double sum = 0;

    for(double value : data) 
      sum += value;

    return (double) Math.round((sum / data.length) * 100) / 100;
  }

  public double calculateStandardDeviation(double[] data) {
    double standardDeviation = 0;
    double mean = calculateAverage(data);

    for (double value: data)
      standardDeviation += Math.pow(value - mean, 2);

    return Math.sqrt(standardDeviation / data.length);
  }

  public double calculateCoefficientOfVariance(double[] data) {
    return Math.round((calculateStandardDeviation(data) / calculateAverage(data)) * 100);
  }

  public String getDataString(int type, int i) {
    // create a "date string" that represents the results of the insertion sorts
    String dataString = "";
    if (type == 0)
      dataString += data[i] + " " 
        + iterativeAvgCounts[i] + " " 
        + iterativeCoefCounts[i] + "% "
        + iterativeAvgTimes[i] + " "
        + iterativeCoefTimes[i] + "% \n";
    else
      dataString += data[i] + " " 
        + recursiveAvgCounts[i] + " " 
        + recursiveCoefCounts[i] + "% "
        + recursiveAvgTimes[i] + " "
        + recursiveCoefTimes[i] + "% \n";

    return dataString;
  }

  public void createReports() {
    // create a txt file that represents the Benchmark Report, saved in the BenchmarkFolders
    String path = "C:\\Users\\kyras\\OneDrive\\Desktop\\Code\\UMGC\\CMSC451\\Project1\\Benchmarks";

    String iterativeFileName = "iterativeBenchmark-" + benchmarkCount + ".txt"; 
    String recursiveFileName = "recursiveBenchmark-" + benchmarkCount + ".txt"; 
  
    try (FileWriter iterativeFile = new FileWriter(path + "\\" + iterativeFileName); FileWriter recursiveFile = new FileWriter(path + "\\" + recursiveFileName);
    ) {
      for (int i =0; i < data.length; i++)
        iterativeFile.write(getDataString(0, i));
      
      System.out.println("New Iterative Benchmark Report: " + iterativeFileName);

      for (int i =0; i < data.length; i++)
        recursiveFile.write(getDataString(1, i));
      
      System.out.println("New Recursive Benchmark Report: " + recursiveFileName);
    } catch (IOException e) {
      System.out.println("An error occurred.");
      e.printStackTrace();
    }
  }
}