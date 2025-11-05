import java.util.Random;
import java.util.function.ToIntBiFunction;

//import Library.LnStrm.*;
//import Library.FnTuple.*;
//import java.util.Random;
//import java.util.function.Function;
//import java.util.function.Consumer;
//import java.util.function.Predicate;
//import java.util.function.BiFunction;
//import java.util.function.BiConsumer;
//import java.util.function.BiPredicate;
//import java.util.function.ToIntBiFunction;

public class Assign06_03 {

    public static <T> void arrayQuickSort(T[] A, ToIntBiFunction<T,T> cmp) {
		// Please implement standard array-based quickSort and make sure 
		// that equal elements are properly handled. In particular, your 
		// testing code should test your implementation on an array of 1M
        if (  A == null || A.length <= 1) return;
        shuffle( A ); sort( A , 0 , A.length - 1 , cmp ); }



    private static final int LIM = 16;

    private static <T> void sort(T[] arr, int l, int h, ToIntBiFunction<T,T> cmp) {
        while ( l <  h ) {
            if ( h - l + 1 <= LIM) { insertion(arr, l, h, cmp); return; }
            T val =  arr[l];
            int lf = l, i = l + 1, rt = h;
            while ( i <= rt ) {
                int c  = cmp.applyAsInt( arr[i] ,  val );
                if (c < 0) swap( arr, lf++, i++ );
                else if (c > 0) swap(arr ,  i, rt-- );
                else i++; }
            if (( lf - l ) < ( h - rt )) {
                sort( arr, l, lf - 1, cmp);
                 l = rt + 1;  } 
				 else { sort(arr, rt + 1, h, cmp); h = lf - 1; } } }

    private static <T> void insertion(T[] arr, int l, int h, ToIntBiFunction<T,T> cmp) {
        for ( int i = l + 1; i <= h; i++) {
            T key  =   arr[i];   int j = i - 1;
            while (  j >= l  &&  cmp.applyAsInt( arr[j], key ) > 0 ) {  arr[j + 1]  =  arr[j];  j--; } arr[ j + 1] = key; }
    }

    private static final Random R = new Random(2025_11_04);





    private static <T> void shuffle(T[] arr) {
        for ( int i  =  arr.length - 1; i >  0;  i--) { int j = R.nextInt( i + 1); swap(arr, i, j); }
    }




    private static <T> void swap(T[] arr, int x, int y) {
        T val  = arr[x];  arr[x] =  arr[y]; arr[y] =  val; }


	// did some other testings as well. passed 
    public static void main(String[] args) {
        Integer[] n =  new Integer[1_000_000]; for ( int i = 0;  i  <  n.length;  i++) n[i] = 0;
        arrayQuickSort( n,  Integer::compare); System.out.println( n[0] + " ," + n[123456] + "," + n[n.length - 1] );
    }
} // end of [public class Assign06_03{...}]
