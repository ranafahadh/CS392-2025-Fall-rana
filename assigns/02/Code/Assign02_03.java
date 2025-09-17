public class Assign02_03 {
    public static boolean solve_3sum(Integer[] A) {
	// Please give a soft qudratic time implementation
	// that solves the 3-sum problem. The function call
	// solve_3sum(A) returns true if and only if there exist
	// indices i, j, and k satisfying A[i]+A[j] = A[k].
	// Why is your implementation soft O(n^2)?
	// did all the testings
	// 3/3 :)
	int val = A.length;
        for (int k = 0; k < val; k++) {
            int val1 = A[k];
            int i = 0, j = val - 1;
            while (i < j) { 
                long val2 = (long) A[i] +  (long) A[j]; 
                if ((val2 == val1))  return true;
                if ((val2 <  val1))  i++; else  j--; } }
        return false; }
    public static void main(String[] argv) {
	// Please write some code here for testing solve_3sum
	Integer[] a1 = {1, 2, 3, 4, 5, 9};
	System.out.println(solve_3sum(a1)); // that should be true :)
	Integer[] a2 = {0, 3, 6, 10};
	System.out.println(solve_3sum(a2)); // that shold be true
	Integer[] a3 = {-2, -1, 4, 8};
	System.out.println(solve_3sum(a3)); // but thisss should be false 
	}}
