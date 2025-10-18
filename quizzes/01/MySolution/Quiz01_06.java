//
// HX: 50 points
//
import java.util.function.ToIntBiFunction;
import Library.FnList.*;
abstract public class Quiz01_06 {
    public static<T>
	FnList<T> someSort
	(FnList<T> xs, ToIntBiFunction<T,T> cmp) {
	// HX-2025-10-15:
	// This one is abstract, that is, not implemented
		return FnListSUtil.quickSort( xs,  cmp); }
    
	
	
	public static
	<T extends Comparable<T>>
	FnList<T> someStableSort
	(FnList<T> xs, ToIntBiFunction<T,T> cmp) {
	// HX-2025-10-15:
	// Please implement a stable sorting method based on
	// someSort, which may not be stable
	class Box<U> { final  U x;  final int  i; Box(U x,int i){ this.x=x;    this.i=i; } }
        FnList<Box<T>>  a = FnListSUtil.fwork$make(out -> { final int[]  val1 =  { 0}; FnListSUtil.foritm(  xs, z  -> { out.accept(new Box<>(z, val1[0])); val1[0]++; }); });
        ToIntBiFunction<Box<T >,Box< T>> cmp2 =   (x, y) -> { int c = cmp.applyAsInt( x.x ,  y.x ); return ( c != 0) ? c : Integer.compare(x.i , y.i); };
        FnList<Box<T>> sorted =  someSort(  a, cmp2);
        return FnListSUtil.fwork$make( out -> FnListSUtil.foritm(sorted, bx -> out.accept(bx.x)) ); }








	public static void main(String[] args) {
        FnList<Integer> xs = FnListSUtil.int1$make(1_000_000);
        ToIntBiFunction<Integer,Integer> val = ( x,  y ) -> ( x  & 1) - ( y &  1);
        FnList<Integer> ys = someStableSort( xs,  val);
        System.out.print( "frst10 :)");
        final int[] c1 = {0};
        FnListSUtil.foritm(ys, v -> { if (c1[0] < 10) System.out.print(v + (c1[ 0 ] < 9 ? " , "   : "")); c1[0]++; });
        System.out.println();
        final Integer[] valt = new  Integer[ 10 ];
        final int[] valss = { 0 };
        FnListSUtil.foritm(ys, v -> {  valt[ valss[ 0 ]  % 10] = v; valss[0]++; });
        System.out.print( "last 10 :) ");
        int start = valss[0] >= 10 ?  (valss[0] %  10)  :  0;
        int reslt = Math.min(valss[0], 10);
         for (int k = 0; k < reslt;  k++) {
            int idx = (start + k) % 10;
             System.out.print(valt[ idx ] + (k  <  reslt -  1 ?  "," : "")); }
        System.out.println(); }
}

////////////////////////////////////////////////////////////////////////.
//
// HX-2025-10-15:
// Please find a way to test someStableSort by
// implementing someSort as quickSort on FnList
// and then use someStableSort to parity-sort the following
// list of 1M integers:
// 0, 1, 2, 3, 4, ..., 999999
// That is, the result of parity-sorting should be:
// 0, 2, ..., 999998, 1, 3, ..., 999999
//
// Note that you should be able to call the quickSort method
// in Library/FnList/FnListSUtil.java; should not do another
// implementation of quickSort in your testing code.
////////////////////////////////////////////////////////////////////////.




   

    

