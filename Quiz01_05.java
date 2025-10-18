//
// HX: 50 points
//
import Library.LnList.*;
// Please see Library/LnList for LnList.java
public class Quiz01_05 {
    public static
	<T extends Comparable<T>>
	LnList<T> LnListQuickSort(LnList<T> xs) {
	// HX-2025-10-12:
	// Please implement quicksort on a linked list (LnList).
	// Note that you are not allowed to modify the definition
	// of the LnList class. You can only use the public methods
	// provided by the LnList class
	if (xs == null || xs.nilq1() || xs.tl1().nilq1()) return xs;
        
	
	int a  = xs.length1();
        java.util.Random  r = new java.util.Random();
        int val1 =  r.nextInt(a);
        LnList<T>  tmp = xs;
        for (int  i = 0; i < val1; i++) tmp = tmp.tl1();
        T  pivot =   tmp.hd1();



        LnList<T> l = LnListSUtil.nil(); LnList<T> e  = LnListSUtil.nil(); LnList<T> m = LnListSUtil.nil();

        while (xs.consq1()) {
            LnList<T>  x = xs;
            LnList<T> y =  xs.unlink();
            xs =  y;
            T n =  x.hd1();
            if (n.compareTo( pivot)  <  0)  l.append1(x);
            else if ((n.compareTo( pivot)   >  0))  m.append1(x);
            else e.append1(x); }

        l = LnListQuickSort( l); m = LnListQuickSort( m);



        if (l.nilq1()) l = e;
        else  l.append1(e);
        if (!m.nilq1()) l.append1(m);

        return l; }



    public static void main (String[] args) {
	// HX-2025-10-12:
	// Please write minimal testing code for LnListQuickSort
    LnList<Integer>  a =  LnListSUtil.cons(4, LnListSUtil.cons( 2,  LnListSUtil.cons(9, LnListSUtil.cons(1, LnListSUtil.cons(7, LnListSUtil.cons(5, LnListSUtil.cons(8, LnListSUtil.cons(3, LnListSUtil.cons(6, LnListSUtil.cons(0, LnListSUtil.nil()))))))))));

        LnList<Integer> b = LnListSUtil.cons(2, LnListSUtil.cons( 2, LnListSUtil.cons( 1, LnListSUtil.cons(1, LnListSUtil.cons(1, LnListSUtil.cons(2, LnListSUtil.nil()))))));

        LnList<Integer> c = LnListSUtil.cons(1, LnListSUtil.cons( 2, LnListSUtil.cons( 3, LnListSUtil.cons(4, LnListSUtil.cons(5, LnListSUtil.nil())))));
        
		LnList<Integer>  d = LnListSUtil.nil();

        a = LnListQuickSort( a);  b = LnListQuickSort( b); c = LnListQuickSort(c); d =  LnListQuickSort(d);

        a.System$out$print1(); System.out.println();  b.System$out$print1(); System.out.println();  c.System$out$print1(); System.out.println(); d.System$out$print1(); System.out.println(); }
}








   
