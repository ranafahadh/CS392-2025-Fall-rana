//
// HX-2025-11-19: 50 points
//
// This question tests your understanding
// of recursion and time analysis involving
// recursion.
// Given a sequence xs, a subsequence of xs
// can be represented as a list of integers
// (representing indices). For instance, given
// xs = "Hello", (0, 2, 4) refers to the subeqence
// "Hlo" (since xs[0] = 'H', xs[2] = 'l', and
// xs[4] = 'o'); (0, 3, 4) also refers to "Hlo".
// The subsequece (0, 2, 4) is to the left of
// the subsequece (0, 3, 4) as (0, 2, 4) is less
// than (0, 3, 4) according to the lexicographic
// ordering.
//
// Here you are asked to implement a function that
// finds the longest leftmost ascending subsequence
// of a given sequence.
// For instance, suppose xs = [1,2,1,2,3,1,2,3,4],
// the longest leftmost ascending subsequence of xs
// is represented by (0, 1, 3, 4, 7, 8) (which refers
// to [1,2,2,3,3,4] in xs).
//
// In order to receive 50 points, your implementation
// should be quadratic time, that is, O(n^2) time and
// you MUST give a brief explanation as to why it is so.
// Otherwise, a working solution receives at most 60%, that
// is, 30 points out of 50 points.
//
import Library.FnList.*;
// Please see Library/FnList for FnList.java
import Library.FnA1sz.*;
// Please see Library/FnA1sz for FnA1sz.java
public class Quiz02_01 {
    public static
	<T extends Comparable<T>>
	FnList<Integer> FnA1szLongestMonoSubsequence(FnA1sz<T> xs) {
	// HX-2025-11-19:
	// This method finds the leftmost longest ascending subsequence
	// of xs. Note that the returned list consists of the indices of
	// the elements of the subsequence.
        int n = xs.length();
        if ((n == 0)) return new FnList<>();
        @SuppressWarnings("unchecked")
        FnList<Integer>[] val = (FnList<Integer>[]) new FnList[n];
        for (int i = 0; i < n; i++) {
            FnList<Integer> bestfori = new FnList<>(i, new FnList<>());
            T xi = xs.getAt(i);
            for (int j = 0; j < i; j++) {
                T xj = xs.getAt(j);
                if (xj.compareTo(xi) <= 0) {
                    FnList<Integer> candid = append(val[j], i);
                    int lenc = candid.length();
                    int lenb = bestfori.length();
                    if (((lenc > lenb)) || (lenc == lenb && comp(candid, bestfori) < 0)) { bestfori = candid; } } }
            val[i] = bestfori; 
        }
        FnList<Integer> thebest = val[0];
        for (int i = 1; i < n; i++) {
            FnList<Integer> c = val[i];
            int lenc = c.length();
            int lenb =  thebest.length();
            if (( lenc > lenb) || (( lenc == lenb && comp(c, thebest) < 0))) { thebest = c; }
        }

        return thebest;  }













   

    private static FnList<Integer> append(FnList<Integer> xs, int indx) {
        if ((  xs == null || xs.nilq())) {  return new FnList<>( indx, new FnList<>());  } 
            else { return new FnList<>( xs.hd(), append(xs.tl(), indx));
             } }





    private static int comp(FnList<Integer> xs, FnList<Integer> ys) {
        while (((!xs.nilq() && !ys.nilq()))) {
            int a = xs.hd();  int b = ys.hd();
            if ((a != b))  return Integer.compare( a, b) ;
            xs = xs.tl();  ys = ys.tl(); }
        if (( xs.nilq() && ys.nilq()))  return 0;
        return xs.nilq() ? -1 : 1;  }







    public static void main (String[] args) {
	// HX-2025-11-19:
	// Please write minimal testing code for FnA1szLongestMonoSubsequence

        Integer[] val = {1,2,1,2,3,1,2,3,4};
        FnA1sz<Integer> val1 = new FnA1sz<>(val);
        FnList<Integer> val2 = FnA1szLongestMonoSubsequence(val1);
        System.out.print("the result for the lis indices    ");
        val2.System$out$print(); System.out.println(); }
}
