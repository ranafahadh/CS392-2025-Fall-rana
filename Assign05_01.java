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
	// HX-2025-10-08: Please implement this method
	// i implemented it in FnListSutil. java :)
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

