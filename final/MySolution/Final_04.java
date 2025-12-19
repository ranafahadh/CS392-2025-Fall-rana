/*
// HX: 50 points for Final_04
// HX: This one tests your RBST implementation done in Quiz02_06.
// In Final_02, pg2701_word$count$listize1() is implemented
// to list words in pg2701.txt according their frequencies.
// In Final_04, you are asked to implement the same functionality
// with a different approach.
*/

import Library.FnList.*;
import Library.FnTuple.*;
import Library.LnStrm.*;

public class Final_04 {
    static FnList<FnTupl2<FnList<Character>, Integer>> pg2701_word$count$listize4() {
	// HX-2025-12-15:
	// Your implementation must contain the following steps:
	// 1. Call pg2701_word$strmize() to get a stream of words
	// 2. Then use the RBST implemented in Quiz02_06 to count the number of
	//    occurrences of each word in the stream of words.
	//    Note that you need to modify your Quiz02_06 implementation to turn
	//    it into an generic associative map for this part.
	// 3. Then figure out a way to turn the RBST-based map into a list WNS
	//    (FnList) of word-count pairs
	// 4. Use the mergesort (mergeSort) in Assign05_01 to sort WNS using
	//    the order (w1, n1) <= (w2, n2) if n1 > n2 or n1 = n2 and w1 <= w2
	// 5. The sorted WNS is the return value of pg2701_word$count$listize4()

        Quiz02_06<FnList<Character>, Integer> mp =
            new Quiz02_06<>((w1, w2) -> Final_02.wordCompare(w1, w2));

        LnStrm<FnList<Character>> ws = Final_01.pg2701_word$strmize();
        while (true) {
            LnStcn<FnList<Character>> cell = ws.eval0();
            if (cell.nilq()) break;
            FnList<Character> w = cell.hd();
            ws = cell.tl();
            Integer n = mp.get(w);
            if (n == null) mp.put(w, 1);
            else mp.put(w, n + 1);
        }

        FnList<FnTupl2<FnList<Character>, Integer>> WNS = mp.toFnList();

        return Assign05_01.mergeSort(
            WNS,
            (p1, p2) -> {
                int n1 = p1.sub1;
                int n2 = p2.sub1;
                if (n1 != n2) return n2 - n1;
                return Final_02.wordCompare(p1.sub0, p2.sub0);
            }
        );
    }

    public static void main (String[] args) {
	// HX-2025-12-16:
	// Please write minimal testing code for pg2701_word$count$listize4()
	// In particular, please print out the first 100 word-count pairs, where
	// each line should contain only one word-count pair.
        FnList<FnTupl2<FnList<Character>, Integer>> xs =
            pg2701_word$count$listize4();

        int k = 0;
        while (!xs.nilq() && k < 100) {
            FnTupl2<FnList<Character>, Integer> p = xs.hd();
            p.sub0.System$out$print();
            System.out.print(" ");
            System.out.println(p.sub1);
            xs = xs.tl();
            k++;
        }
        return /*void*/;
    }
}
