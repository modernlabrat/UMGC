class Main {
  /*
   * Preconditions: n >= 1.
   * Postconditions: Returns 3(n(n+1)/2) - 2n
   * 
   * @param n => number of terms
   */
  static int pentagonalRecursion(int n) { 
    if (n == 1) return n;
    else return pentagonalRecursion(n - 1) + (3 * n - 2);
  }


  static int pentagonal(int n) { 
    int result = 0; 
    
    for (int i = 1; i <= n; i++) 
      result += 3 * i - 2; 
    return result; 
  } 


  public static void main(String[] args) {
    System.out.println(pentagonalRecursion(17));
    System.out.println(pentagonal(17));
  }
}