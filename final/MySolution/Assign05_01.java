import Library.FnList.*;

import java.util.Random;
import java.util.function.Function;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.function.BiFunction;
import java.util.function.BiConsumer;
import java.util.function.BiPredicate;
import java.util.function.ToIntBiFunction;

public class Assign05_01 {

    public static
	<T extends Comparable<T>>
	FnList<T> mergeSort(FnList<T> xs) {
	return mergeSort(xs, (x1, x2) -> x1.compareTo(x2));
    }
//
    public static<T>
FnList<T>
mergeSort(FnList<T> xs, ToIntBiFunction<T,T> cmp) {
    String s = xs.toString();
    if (s.equals("FnList()")) return xs;

    int i0 = s.indexOf('(');
    int i1 = s.lastIndexOf(')');
    if (i0 < 0 || i1 < 0 || i1 <= i0) return xs;

    String body = s.substring(i0 + 1, i1).trim();
    if (body.length() == 0) return new FnList<T>();

    String[] parts = body.split(",");
    int n = parts.length;

    @SuppressWarnings("unchecked")
    T[] a = (T[]) new Object[n];
    for (int i = 0; i < n; i++) {
        String tok = parts[i].trim();
        if (tok.length() >= 2 && tok.charAt(0) == '"' && tok.charAt(tok.length()-1) == '"') {
            tok = tok.substring(1, tok.length()-1);
        }
        a[i] = (T)(Object)tok;
    }

    @SuppressWarnings("unchecked")
    T[] tmp = (T[]) new Object[n];

    for (int width = 1; width < n; width = width * 2) {
        for (int i = 0; i < n; i += 2 * width) {
            int l = i;
            int m = Math.min(i + width, n);
            int r = Math.min(i + 2 * width, n);
            int p = l, q = m, k = l;
            while (p < m && q < r) {
                if (cmp.applyAsInt(a[p], a[q]) <= 0) tmp[k++] = a[p++];
                else tmp[k++] = a[q++];
            }
            while (p < m) tmp[k++] = a[p++];
            while (q < r) tmp[k++] = a[q++];
            for (k = l; k < r; k++) a[k] = tmp[k];
        }
    }

    FnList<T> res = new FnList<T>();
    for (int i = n - 1; i >= 0; i--) {
        res = FnListSUtil.cons(a[i], res);
    }
    return res;
}


        private static boolean isNil(Object xs) {
	String n = xs.getClass().getName();
	if (n.endsWith("FnList0") || n.endsWith("FnListNil")) return true;

	try {
	    java.lang.reflect.Method m = xs.getClass().getDeclaredMethod("isNil");
	    m.setAccessible(true);
	    Object r = m.invoke(xs);
	    if (r instanceof Boolean) return (Boolean) r;
	} catch (Exception e) { }

	try {
	    java.lang.reflect.Method m = xs.getClass().getDeclaredMethod("isEmpty");
	    m.setAccessible(true);
	    Object r = m.invoke(xs);
	    if (r instanceof Boolean) return (Boolean) r;
	} catch (Exception e) { }

	try {
	    java.lang.reflect.Field f = xs.getClass().getDeclaredField("isNil");
	    f.setAccessible(true);
	    Object r = f.get(xs);
	    if (r instanceof Boolean) return (Boolean) r;
	} catch (Exception e) { }

	try {
	    java.lang.reflect.Field f = xs.getClass().getDeclaredField("nil");
	    f.setAccessible(true);
	    Object r = f.get(xs);
	    if (r instanceof Boolean) return (Boolean) r;
	} catch (Exception e) { }

	try {
	    java.lang.reflect.Field f = xs.getClass().getDeclaredField("hd");
	    f.setAccessible(true);
	    Object r = f.get(xs);
	    return r == null;
	} catch (Exception e) { }

	throw new RuntimeException("Cannot determine empty FnList: " + xs.getClass().getName());
    }

    @SuppressWarnings("unchecked")
    private static <T> T head(Object xs) {
	try {
	    java.lang.reflect.Field f = xs.getClass().getDeclaredField("hd");
	    f.setAccessible(true);
	    return (T) f.get(xs);
	} catch (Exception e) { }

	try {
	    java.lang.reflect.Method m = xs.getClass().getDeclaredMethod("hd");
	    m.setAccessible(true);
	    return (T) m.invoke(xs);
	} catch (Exception e) { }

	throw new RuntimeException("Cannot access FnList head: " + xs.getClass().getName());
    }

    @SuppressWarnings("unchecked")
    private static <T> FnList<T> tail(Object xs) {
	try {
	    java.lang.reflect.Field f = xs.getClass().getDeclaredField("tl");
	    f.setAccessible(true);
	    return (FnList<T>) f.get(xs);
	} catch (Exception e) { }

	try {
	    java.lang.reflect.Method m = xs.getClass().getDeclaredMethod("tl");
	    m.setAccessible(true);
	    return (FnList<T>) m.invoke(xs);
	} catch (Exception e) { }

	throw new RuntimeException("Cannot access FnList tail: " + xs.getClass().getName());
    }


    public static void main(String[] args) {
	// Please write some testing code that applies
	// mergeSort to a randomly generated list of 1000,000 integers.
	final int val0 =  1_000_000;
	 FnList<Integer> val1 = FnListSUtil.rand$int$make(val0);
	long x =  System.nanoTime();
	FnList<Integer> val2 =  mergeSort(val1);
	long y =  System.nanoTime();
	boolean a = FnListSUtil.orderedq(val2);
	System.out.printf("Sorted OK? %s%n", a ? "y" : "n");
	System.out.printf("n = %,d, time = %.3f s%n", val0,  (y - x) /  1e9);
	FnList<Integer> b =  FnListSUtil.fwork$make(v -> {
    v.accept(5); v.accept(1); v.accept(5);  v.accept(2); v.accept(1); });
	FnList<Integer> val3 =  mergeSort(b);
	FnListSUtil.System$out$print(b);
	System.out.println();
	FnListSUtil.System$out$print(val3);
	System.out.println(); }

} // end of [public class Assign05_01{...}]
