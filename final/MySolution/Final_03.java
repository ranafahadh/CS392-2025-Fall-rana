/*
// HX: 50 points for Final_03
// HX: This one tests your hash map implementation
// In Final_02, pg2701_word$count$listize2() is implemented
// to list words in pg2701.txt according their frequencies.
// In Final_03, you are asked to implement the same functionality
// with a different approach.
*/

import Library.FnList.*;
import Library.FnTuple.*;
import Library.LnStrm.*;

public class Final_03 {

    static FnList<FnTupl2<FnList<Character>, Integer>> pg2701_word$count$listize3() {
	// HX-2025-12-15:
	// Your implementation must contain the following steps:
	// 1. Call pg2701_word$strmize() to get a stream of words
	// 2. Then use the hash map implemented in Assign08_02 (open addressing)
	//    to count the number of occurrences of each word in the stream of words
	// 3. Then figure out a way to turn the hash map into a list WNS (FnList) of
	//    word-count pairs
	// 4. Use the mergesort (mergeSort) in Assign05_01 to sort WNS using
	//    the order (w1, n1) <= (w2, n2) if n1 > n2 or n1 = n2 and w1 <= w2
	// 5. The sorted WNS is the return value of pg2701_word$count$listize3()

        LnStrm<FnList<Character>> ws = Final_01.pg2701_word$strmize();
        Assign08_02<Integer> mp = new Assign08_02<>(20011);

        LnStrm<FnList<Character>> cur = ws;
        while (true) {
            LnStcn<FnList<Character>> c = cur.eval0();
            if (c.nilq()) break;
            FnList<Character> w = c.hd();
            String s = Final_02.wordToString(w);
            mp.insert$raw(s, 1);
            cur = c.tl();
        }

        FnList<FnTupl2<FnList<Character>, Integer>> WNS = new FnList<>();

        LnStrm<FnTupl2<String, FnList<Integer>>> es = mp.strmize();
        while (true) {
            LnStcn<FnTupl2<String, FnList<Integer>>> c = es.eval0();
            if (c.nilq()) break;
            FnTupl2<String, FnList<Integer>> e = c.hd();
            int cnt = e.sub1.length();
            String s0 = e.sub0;
			FnList<Character> w = new FnList<>();
			for (int i = s0.length() - 1; i >= 0; i--)
    			w = new FnList<>(s0.charAt(i), w);

            WNS = new FnList<>(new FnTupl2<>(w, cnt), WNS);
            es = c.tl();
        }

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
	// Please write minimal testing code for pg2701_word$count$listize3()
	// In particular, please print out the first 100 word-count pairs, where
	// each line should contain only one word-count pair.

        FnList<FnTupl2<FnList<Character>, Integer>> xs =
            pg2701_word$count$listize3();

        int k = 0;
        FnList<FnTupl2<FnList<Character>, Integer>> cur = xs;

        while (!cur.nilq() && k < 100) {
            FnTupl2<FnList<Character>, Integer> p = cur.hd();
            System.out.print(Final_02.wordToString(p.sub0));
            System.out.print(" ");
            System.out.println(p.sub1);
            cur = cur.tl();
            k++;
        }

        return;
    }
}
