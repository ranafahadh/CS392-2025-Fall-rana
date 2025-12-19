/*
  HX-2025-09-15: 10 points
*/
public class Assign03_01 {
    //
    // HX-2025-09-15:
    // This implementation of f91
    // is not tail-recursive. Please
    // translate it into a version that
    // is tail-recursive
    //
    
    static int f91(int n) {
	return helper(n , 0); //return the helper fn i made that takes two paramaters   
    }


    private static int helper(int n, int t) {
        // the  base case
        if (n > 100) { int val = n - 10 ;  if (t == 0) {return val;  } 
        else { return helper(val, t - 1);} } else {
            // the tail
            return helper(n + 11, t + 1); }  }
    


    public static void main(String[] argv) {
	// Please write some testing code here
    //testss all good 
    int[] t = {55, 10, 99, 100, 101, 110, 20, 54, 10000};
    for (int n : t) { System.out.println(n + " gives " + f91(n)); } }
}
