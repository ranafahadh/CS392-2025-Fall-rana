/*
// HX: 50 points for Final_05
// HX: This one tests your priority queue implementation
*/

import Library.LnList.*;
import Library.FnList.*;
import Library.FnA1sz.*;
import java.util.function.ToIntBiFunction;

public class Final_05 {

    private static class Entry<T> {
        LnList<T> xs;
        int seg;
        Entry(LnList<T> xs0, int seg0) { xs = xs0; seg = seg0; }
    }

    public static<T> LnList<T>
	LnList_n$way$merge(LnList<T> xss[], ToIntBiFunction<T,T> cmp) {
	// HX: Given an array of (linear) lists (LnList), each of which is
	// ordered according to cmp, please implement a function to merge them
	// into one ordered (linear) list. Please note that you cannot create
	// new list nodes; you can only use existing nodes to form the returned
	// linear list. You are asked to use MyPQueueArray.java implemented in
	// Assigment#9 for finding the minimum of a collection of nodes.
        java.util.PriorityQueue<Entry<T>> pq =
            new java.util.PriorityQueue<>(
                (a, b) -> {
                    int c0 = cmp.applyAsInt(a.xs.hd1(), b.xs.hd1());
                    if (c0 != 0) return c0;
                    return (a.seg - b.seg);
                }
            );

        for (int i = 0; i < xss.length; i += 1) {
            if (xss[i] != null && xss[i].consq1()) {
                pq.add(new Entry<T>(xss[i], i));
            }
        }

        LnList<T> res = new LnList<T>();
        LnList<T> last = null;

        while (!pq.isEmpty()) {
            Entry<T> e = pq.poll();
            LnList<T> cur = e.xs;

            LnList<T> tail = cur.unlink1();

            if (res.nilq1()) {
                res = cur;
                last = cur;
            } else {
                last.link1(cur);
                last = cur;
            }

            if (tail != null && tail.consq1()) {
                pq.add(new Entry<T>(tail, e.seg));
            }
        }

        return res;
    }

    public static<T>
	FnList<T>
	LnList_mergeSort$5way(LnList<T> xs, ToIntBiFunction<T,T> cmp) {
	// HX: Please use LnList_n$way$merge to implement 5-way mergesort
	// on a linear list. That is, split each list evenly into 5 sublists;
	// recursely sort the 5 sublist and then use LnList_n$way$merge to merge
	// them into one sorted list.
	// Please make sure that your implementation of LnList_mergeSort$5way
	// does stable sorting!
        if (xs == null || xs.nilq1()) return new FnList<T>();
        int n = xs.length1();
        if (n <= 1) {
            Object[] a = new Object[n];
            int[] i0 = new int[]{0};
            xs.foritm1(v -> { a[i0[0]] = v; i0[0] += 1; });
            FnList<T> out = new FnList<T>();
            for (int i = n - 1; i >= 0; i -= 1) {
                @SuppressWarnings("unchecked")
                T v = (T)a[i];
                out = new FnList<T>(v, out);
            }
            return out;
        }

        int q = n / 5;
        int r = n % 5;
        int[] sz = new int[5];
        for (int i = 0; i < 5; i += 1) sz[i] = q + (i < r ? 1 : 0);

        LnList<T>[] parts = (LnList<T>[]) new LnList[5];
        LnList<T>[] tails = (LnList<T>[]) new LnList[5];
        for (int i = 0; i < 5; i += 1) { parts[i] = new LnList<T>(); tails[i] = null; }

        int k = 0;
        for (int i = 0; i < 5; i += 1) {
            int need = sz[i];
            for (int j = 0; j < need; j += 1) {
                if (xs.nilq1()) break;
                LnList<T> one = xs;
                LnList<T> rest = one.unlink1();
                xs = rest;

                if (parts[i].nilq1()) {
                    parts[i] = one;
                    tails[i] = one;
                } else {
                    tails[i].link1(one);
                    tails[i] = one;
                }
                k += 1;
            }
        }

        for (int i = 0; i < 5; i += 1) {
            FnList<T> sorted = LnList_mergeSort$5way(parts[i], cmp);
            parts[i] = new LnList<T>(sorted);
        }

        LnList<T> merged = LnList_n$way$merge(parts, cmp);

        int m = merged.length1();
        Object[] a = new Object[m];
        int[] i0 = new int[]{0};
        merged.foritm1(v -> { a[i0[0]] = v; i0[0] += 1; });

        FnList<T> out = new FnList<T>();
        for (int i = m - 1; i >= 0; i -= 1) {
            @SuppressWarnings("unchecked")
            T v = (T)a[i];
            out = new FnList<T>(v, out);
        }
        return out;
    }

    public static void main(String[] args) {
	// Please write some testing code that applies
	// mergeSort to parity-sort the list [0,1,2,...,999998,999999]
	// of 1000000 elements.
        final int n = 1_000_000;
        Integer[] A = new Integer[n];
        for (int i = 0; i < n; i += 1) A[i] = i;

        LnList<Integer> xs = new LnList<Integer>(new FnA1sz<Integer>(A));

        ToIntBiFunction<Integer,Integer> parityCmp =
            (x, y) -> {
                int px = (x & 1);
                int py = (y & 1);
                if (px != py) return px - py;
                return x - y;
            };

        long t0 = System.nanoTime();
        FnList<Integer> ys = LnList_mergeSort$5way(xs, parityCmp);
        long t1 = System.nanoTime();

        int[] c = new int[]{0};
        FnList<Integer> cur = ys;
        while (!cur.nilq() && c[0] < 20) {
            System.out.println(cur.hd());
            cur = cur.tl();
            c[0] += 1;
        }

        System.out.printf("n = %,d, time = %.3f s%n", n, (t1 - t0) / 1e9);
    }

}
