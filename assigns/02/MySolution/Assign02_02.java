import java.util.Arrays;

public class Assign02_02 {
    /*
      HX-2025-02-13: 10 points
      Recursion is a fundamental concept in programming.
      However, the support for recursion in Java is very limited.
      Nontheless, we will be making extensive use of recursion in
      this class.
     */

    /*
    // This is a so-called iterative implementation:
    public static <T extends Comparable<T> > int indexOf(T[] a, T key) {
        int lo = 0;
        int hi = a.length - 1;
        while (lo <= hi) {
            // Key is in a[lo..hi] or not present.
            final int mid = lo + (hi - lo) / 2;
	    final int sign = key.compareTo(a[mid]);
            if      (sign < 0) hi = mid - 1;
            else if (sign > 0) lo = mid + 1;
            else return mid;
        }
        return -1;
    }
    */

    // did some testenings
    // 2/3
    public static <T extends Comparable<T> > int indexOf(T[] a, T key) {
	// Please give a recursive implementation of 'indexOf' that is
	// equivalent to the above one
    
    
        return indexOf(a, key, 0, a.length - 1);}





    private static <T extends Comparable<T>> int indexOf(T[] a, T key, int l, int h) {
        if (l > h) return -1;
        int valm = l + (h - l) / 2; int val = key.compareTo(a[valm]);
        if (val < 0)  return indexOf(a, key, l, valm - 1);
        if (val > 0)  return indexOf(a, key, valm + 1, h);
        return valm; }




    
    public static void main(String[] argv) {
	// Please write some testing code for your implementation of 'indexOf'
        Integer[] val = {1, 3, 4, 7, 11, 18, 21};
        System.out.println(indexOf(val, 7));   // that should be 3 :)
        System.out.println(indexOf(val, 2));   // that should be -1 
        String[] words = {"jndj", "bkee", "jhdfj", "kjd"};
        System.out.println(indexOf(words, "jhdfj"));  // that should be 2
        System.out.println(indexOf(words, "ijd"));  // that should be -1
    }
}
