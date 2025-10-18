//
// HX: 40 points
//
import Library.LnList.*;
// Please see Library/LnList for LnList.java
public class Quiz01_04 {
    public static
	<T extends Comparable<T>>
	LnList<T> LnListInsertSort(LnList<T> xs) {
	// HX-2025-10-12:
	// Please implement (stable) insert sort on a
	// linked list (LnList).
	// Note that you are not allowed to modify the definition
	// of the LnList class. You can only use the public methods
	// provided by the LnList class
    if (xs == null || xs.nilq1()) return xs;
        LnList<T> d = LnListSUtil.nil(); 
        while (!xs.nilq1()) {
            LnList<T> curr =  xs;           
            LnList<T> rst =  xs.unlink();  
            xs = rst;
            T val = curr.hd1(); 
            LnList<T> l  =  LnListSUtil.nil();
            LnList<T> r  = d;
            while (r.consq1() && r.hd1().compareTo(val) <= 0) {
                LnList<T> tail  = r.unlink();   
                if (l.nilq1()) l = r;    
                 else l.append1(r);
                r =  tail;  }
            if (l.nilq1()) l  =  curr;
            else l.append1( curr);
            l.append1(r);
            d = l; } return  d; } 

    public static void main(String[] args) {
	// HX-2025-10-12:
	// Please write minimal testing code for LnListInsertSort
	LnList<Integer> a = LnListSUtil.cons(4, LnListSUtil.cons(1, LnListSUtil.cons(3, LnListSUtil.cons(2, LnListSUtil.cons(5, LnListSUtil.nil())))));
	LnList<Integer> b = LnListSUtil.cons(2, LnListSUtil.cons(2, LnListSUtil.cons(1, LnListSUtil.cons(1, LnListSUtil.cons(2, LnListSUtil.nil())))));
    LnList<Integer> c = LnListSUtil.nil();
	
    a =  LnListInsertSort(a);  b  =  LnListInsertSort(b); c =   LnListInsertSort(c);
    a.System$out$print1();  System.out.println();  b.System$out$print1();  System.out.println();  c.System$out$print1();  System.out.println();
    }  }







    
        

