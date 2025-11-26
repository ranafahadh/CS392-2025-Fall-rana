//
// HX: 50 points
// Here we revisit a question on quiz01 (Quiz01_03).
// Instead of sorting 10 elements without recursion,
// you are asked to sort up to 1000 elements without
// recursion.
// Hint: Think about building a tree of commands for
// swapping array elements.
//
public class Quiz02_02 {
    public static
	<T extends Comparable<T>>
	void sort1000WithNoRecursion(T[] A) {
	// HX-2025-11-20:
	// A is an array of size at most 1000.
	// Please implement a sorting algorithm
	// WITHOUT recursion that can effectively
	// sort A.
        int n = A.length;
        if ((n <= 1)) return; //thebase

        int[] loS =  new int[1000];
        int[] hiS = new int[1000];
        int valtop =   0;
        loS[valtop] = 0;
        hiS[valtop] = (n - 1);
        valtop += 1;
        while ((valtop > 0)) {   
            valtop -= 1;
            int lo = loS[valtop]; int hi = hiS[valtop];
            if (lo >= hi) { continue; }
            int i = lo; int j = hi;
            T p = A[(lo + ((hi - lo)) / 2)];

           


            while (i <= j) {
                while (A[i].compareTo(p) < 0) {
                    i += 1;
                }
                while (A[j].compareTo(p) > 0) {
                    j -= 1;
                }
                if (i <= j) {
                    T t = A[i];
                    A[i] = A[j];
                    A[j] = t;
                    i += 1;  j -= 1; }
            }

            
            if (( lo < j)) {
                loS[valtop] = lo;
                hiS[valtop] = j;
                valtop += 1; }

            if ((i < hi)) {
                loS[valtop] = i;
                hiS[valtop] = hi;
                valtop += 1; }
        }
    }









    public static void main (String[] args) {
	// HX-2025-11-19:
	// Please write minimal testing code for FnA1szLongestMonoSubsequence

        Integer[] val = { 5, 1, 4, 2, 8, 3, 7, 6 };
        sort1000WithNoRecursion(val);
        System.out.print("sorted numbers   ");
        for (int x : val) {
            System.out.print(x + " "); }
        System.out.println();



        String[] words  = {  "banana", "apple", "cherry", "date", "boston", "riyadh", "sam" };
        sort1000WithNoRecursion(words);
        System.out.print("sorted words   ");
        for (String s : words) {
            System.out.print(s + " "); }
        System.out.println();
    }
}
