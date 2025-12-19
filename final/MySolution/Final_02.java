/*
 // HX: 50 points for Final_02
 // HX: This one tests your quicksort and mergesort
 // In Final_01, pg2701_word$strmize() is implemented
 // that lists all the words in pg2701.txt. Here, you
 // are asked to generate FnList of pairs; each pair consists
 // of a word (FnList<Character>) and a count (Integer) such that
 // the count is the number of occurrences of the word in pg2701.txt.
 // Note that a lower case letter is considered the same as its
 // corresponding upper case. For instance, "Whale" and "whale"
 // are considered the same word.
 */

import Library.FnList.*;
import Library.FnTuple.*;
import Library.LnStrm.*;

import java.util.ArrayList;

public class Final_02 {

    static int wordCompare(FnList<Character> w1, FnList<Character> w2) {
        String s1 = Final_01.wordToString(w1).toLowerCase();
        String s2 = Final_01.wordToString(w2).toLowerCase();
        return s1.compareTo(s2);
    }

    static FnList<FnTupl2<FnList<Character>, Integer>> pg2701_word$count$listize2() {
	// HX-2025-12-15:
	// Your implementation must contain the following steps:
	// 1. Call pg2701_word$strmize() to get a stream of words
	// 2. Turn this stream into an array A1 of words (FnList<Character>[])
	// 3. Call the quicksort (arrayQuickSort) done in Assign06_03 to sort A1
	// 4. Use sorted A1 to generate a list L2 of word-count pairs
	// 5. Use the mergesort (mergeSort) in Assign05_01 to sort L2 using
	//    the order (w1, n1) <= (w2, n2) if n1 > n2 or n1 = n2 and w1 <= w2
	// 6. The sorted L2 is the return value of pg2701_word$count$listize2()

        LnStrm<FnList<Character>> ws = Final_01.pg2701_word$strmize();

        ArrayList<FnList<Character>> buf = new ArrayList<>();
        while (true) {
            LnStcn<FnList<Character>> cell = ws.eval0();
            try {
                FnList<Character> w = cell.hd();
                buf.add(w);
                ws = cell.tl();
            } catch (Exception ex) {
                break;
            }
        }

        @SuppressWarnings("unchecked")
        FnList<Character>[] A1 = (FnList<Character>[]) new FnList[buf.size()];
        for (int j = 0; j < buf.size(); j += 1) A1[j] = buf.get(j);

        Assign06_03.arrayQuickSort(A1, (x, y) -> wordCompare(x, y));

        FnList<FnTupl2<FnList<Character>, Integer>> acc = new FnList<>();
        int n = A1.length;
        int i = 0;
        while (i < n) {
            FnList<Character> w = A1[i];
            int cnt = 1;
            i += 1;
            while (i < n && wordCompare(A1[i], w) == 0) {
                cnt += 1;
                i += 1;
            }
            acc = new FnList<>(new FnTupl2<>(w, cnt), acc);
        }
        FnList<FnTupl2<FnList<Character>, Integer>> L2 = acc.reverse();

        return Assign05_01.mergeSort(
            L2,
            (p1, p2) -> {
                int n1 = p1.sub1;
                int n2 = p2.sub1;
                if (n1 != n2) return (n2 - n1);
                return wordCompare(p1.sub0, p2.sub0);
            }
        );
    }
	public static String wordToString(FnList<Character> w) {
    StringBuilder sb = new StringBuilder();
    FnList<Character> cur = w;
    while (true) {
        try {
            Character c = cur.hd();      
            sb.append(c);
            cur = cur.tl();
        } catch (Exception exn) {
            break;
        }
    }
    return sb.toString();
}
    public static void main (String[] args) {
	// HX-2025-12-16:
	// Please write minimal testing code for pg2701_word$count$listize2()
	// In particular, please print out the first 100 word-count pairs, where
	// each line should contain only one word-count pair.
	FnList<FnTupl2<FnList<Character>, Integer>> xs = pg2701_word$count$listize2();
	int k = 0;
	while (k < 100) {
	    FnTupl2<FnList<Character>, Integer> p = xs.hd();
		System.out.print(wordToString(p.sub0));
		System.out.print(" ");
		System.out.println(p.sub1);
		xs = xs.tl();
	    k = k + 1;
	}
	return /*void*/;
}


}
