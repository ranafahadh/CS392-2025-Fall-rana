import Library.FnList.*;
    
import java.util.Random;
import java.util.function.Function;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.function.BiFunction;
import java.util.function.BiConsumer;
import java.util.function.BiPredicate;
import java.util.function.ToIntBiFunction;

public class Assign05_02 {

    public static
	<T extends Comparable<T>>
	FnList<T> insertSort(FnList<T> xs) {
	return insertSort(xs, (x1, x2) -> x1.compareTo(x2));
    }
//
    public static<T>
	FnList<T>
	insertSort(FnList<T> xs, ToIntBiFunction<T,T> cmp) {
	// HX-2025-10-08: Please implement this method
	// i implemented it in FnListSutil. java :)
    }

    public static void main(String[] args) {
	// Please write some testing code that applies
	// insertSort to the following list of 1M numbers:
	// 1, 0, 3, 2, 5, 4, 7, 6, 9, 8, 11, 10, ..., 999999, 999998.
	FnList<Integer> val0 = FnListSUtil.fwork$make(v -> {
    v.accept(5); v.accept(2);   v.accept(4); v.accept(6);  v.accept(1); v.accept(3); });
	 FnList<Integer> val1 =   FnListSUtil.insertSort(val0);
	System.out.println("k? " + FnListSUtil.orderedq(val1));
	 FnList<Integer> x = FnListSUtil.fwork$make(v -> {
    v.accept(5);  v.accept(1); v.accept(5); v.accept(2); v.accept(1); });
	FnList<Integer> y =   FnListSUtil.insertSort(x);
	FnListSUtil.System$out$print(x);
	 System.out.println();
	FnListSUtil.System$out$print(y);
	System.out.println();   }

} // end of [public class Assign05_02{...}]
