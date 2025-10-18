//
// HX: 20 points
//
import Library.FnA1sz.*;
import Library.FnA1sz.*;
import Library.FnList.*;
import java.util.function.Consumer;
import java.util.function.Function; 

// Please see Library/FnA1sz for FnA1sz.java
public class Quiz01_01 {
    public static
	<T extends Comparable<T>>
	int FnA1szBinarySearch(FnA1sz<T> A, T key) {
	// HX-2025-10-12:
	// Please implement binary search on a sorted functional array (FnA1sz)
	// that returns the largest index i such that key >= A[i] if such i exists,
	// or the method returns -1. The comparison function should be the compareTo
	// method implemented by the class T.
	int val1 =  (A == null) ? 0 : A.length(); // size
        if (val1 == 0) return -1;
        int  x = 0, y = val1  - 1, z = -1; 
        while (x <= y) {
            int m =  x +   ((y - x)   >>> 1); // mid
            T tmp = A.getAt(m); 
            int cmp = key.compareTo(tmp);
            if (cmp >=  0) {
                 z = m;
                x = m + 1; } 
				else {
                y  = m - 1;  } } 
				 return z;    }
    public static void main (String[] args) {
	// HX-2025-10-12:
	// Please write minimal testing code for FnA1szBinarySearch

	Function<int[], FnA1sz<Integer>> val2 = arr -> {
            FnList<Integer> val3 = FnListSUtil.fwork$make(k -> {
                for (int n : arr) k.accept(n);  });
            return FnA1szSUtil.list$make(val3); };





	FnA1sz<Integer> a0 = FnA1szSUtil.list$make(FnListSUtil.fwork$make(k -> {})); 
        FnA1sz<Integer> a1 = val2.apply(new  int[]{1, 3, 5, 7, 9 }); 
        FnA1sz<Integer> a2 = val2.apply(new int[]{2, 2,  2, 2}); 
        FnA1sz<Integer> a3 = val2.apply(new  int[]{0,  1, 2, 3,  4, 5}); 
        System.out.println(FnA1szBinarySearch(a0, 10)); System.out.println(FnA1szBinarySearch(a1, 0)); System.out.println(FnA1szBinarySearch(a1, 6)); System.out.println(FnA1szBinarySearch(a1, 9));
        System.out.println(FnA1szBinarySearch(a2, 2)); System.out.println(FnA1szBinarySearch(a3, 99)); }
}
