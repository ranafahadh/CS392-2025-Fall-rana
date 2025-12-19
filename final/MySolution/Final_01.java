/*
// HX: 20 points for Final_01
// A word consists of a sequence of
// letters ([a-z]+[A-Z]) plus aprostrophe (')
// And words are separated by non-letters-aprostrophe
// (such as blanks, punctuations, etc.) in pg2701.txt.
*/

import Library.FnList.*;
import Library.LnStrm.*;

public class Final_01 {

    static boolean isWordChar(char c) {
        return (c >= 'a' && c <= 'z')
            || (c >= 'A' && c <= 'Z')
            || (c == '\'');
    }

    static char toLowerIfUpper(char c) {
        if (c >= 'A' && c <= 'Z') return (char)(c - 'A' + 'a');
        return c;
    }

    static LnStrm<FnList<Character>> pg2701_word$strmize() {
	// HX-2025-12-16:
	// Please construct a stream of words contained in the
	// file Data/pg2701.txt
	// Note that a word is represented as a list of characters
	// Also every upper case letter in the original text should
	// be turned into its corresponding lower case.
	// This stream should be built on top of pg2701_char$strmize
	// which is already implemented in Final_00.
	// In particular, you should NOT use Java library function
	// for processing files!
        return wordStrmFrom(Final_00.pg2701_char$strmize());
    }

    private static LnStrm<Character> skipNonWord(LnStrm<Character> cs) {
    LnStcn<Character> cell = cs.eval0();
    if (cell.nilq())
        return new LnStrm<Character>(() -> new LnStcn<Character>());
    char c = cell.hd();
    if (isWordChar(c)) {
        final char c0 = c;
        return new LnStrm<Character>(() -> new LnStcn<Character>(c0, cell.tl()));
    }
    return skipNonWord(cell.tl());
}


    private static class WordRead {
        FnList<Character> word;
        LnStrm<Character> rest;
        WordRead(FnList<Character> w, LnStrm<Character> r) {
            word = w;
            rest = r;
        }
    }

    private static WordRead readWord(LnStrm<Character> cs) {
    LnStcn<Character> cell = cs.eval0();
    if (cell.nilq())
        return new WordRead(new FnList<Character>(),
            new LnStrm<Character>(() -> new LnStcn<Character>()));

    char c = cell.hd();

    if (!isWordChar(c)) {
        final char c0 = c;
        LnStrm<Character> rebuilt =
            new LnStrm<Character>(() -> new LnStcn<Character>(c0, cell.tl()));
        return new WordRead(new FnList<Character>(), rebuilt);
    }

    c = toLowerIfUpper(c);
    WordRead tailRes = readWord(cell.tl());
    return new WordRead(new FnList<Character>(c, tailRes.word), tailRes.rest);
}


    

    private static LnStrm<FnList<Character>> wordStrmFrom(LnStrm<Character> cs0) {
        return new LnStrm<FnList<Character>>(
            () -> {
                LnStrm<Character> cs = skipNonWord(cs0);
                LnStcn<Character> peek = cs.eval0();
                if (peek.nilq())
                    return new LnStcn<FnList<Character>>();
                LnStrm<Character> rebuilt =
                    new LnStrm<Character>(() -> new LnStcn<Character>(peek.hd(), peek.tl()));
                WordRead wr = readWord(rebuilt);
                return new LnStcn<FnList<Character>>(wr.word, wordStrmFrom(wr.rest));
            }
        );
    }

    static String wordToString(FnList<Character> w) {
        StringBuilder sb = new StringBuilder();
        FnList<Character> xs = w;
        while (xs.consq()) {
            sb.append(xs.hd());
            xs = xs.tl();
        }
        return sb.toString();
    }

    public static void main (String[] args) {
	// HX-2025-12-16:
	// Please write minimal testing code for pg2701_word$strmize()
        LnStrm<FnList<Character>> ws = pg2701_word$strmize();
        int k = 0;
        while (k < 30) {
            LnStcn<FnList<Character>> cell = ws.eval0();
            if (cell.nilq()) break;
            System.out.println(wordToString(cell.hd()));
            ws = cell.tl();
            k += 1;
        }
	return /*void*/;
    }
}
