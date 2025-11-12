
import Library.FnList.*;
import Library.LnStrm.*;
import Library.FnGtree.*;

import java.util.function.Consumer;

public class Assign07_01 {
//
    public static<T> LnStrm<T>
	BFirstEnumerate(FnGtree<T> root) {
	// Please add your code here
	// You need to use your solution to Assign04_02
	// If you need to change your code for Assign04_02
	// Please detail what changes are made
	// Did not change anything 
    if (root == null) return new FnList<T>().toLnStrm();
	MyDequeList<FnGtree<T>> a = new MyDequeList<>();
	a.renque(root);
	FnList<T> val = new FnList<>();
	while (!a.isEmpty()) {
	    FnGtree<T> b = a.fdeque();
	    val = new FnList<>(b.value(), val);
	    FnList<FnGtree<T>> c = b.children();
	    while (!c.nilq())  {
		a.renque(c.hd());
		c = c.tl();
	    } }  FnList<T> val1 = val.reverse(); return val1.toLnStrm(); }
//
    public static<T> LnStrm<T>
	DFirstEnumerate(FnGtree<T> root) {
	// Please add your code here
	// You need to use your solution to Assign04_02
	// If you need to change your code for Assign04_02
	// Please detail what changes are made
	// same here 
    if (root == null) return new FnList<T>().toLnStrm();
	MyDequeList<FnGtree<T>> a = new MyDequeList<>();
	a.fenque(root);
	FnList<T> val = new FnList<>();
	while ( !a.isEmpty()) {
	    FnGtree<T> b = a.fdeque();
	    val = new FnList<>( b.value(), val);
	    FnList<FnGtree<T>> c = b.children().reverse();
	    while (!c.nilq()) {
		 a.fenque(c.hd());
		c = c.tl();
	    } } FnList<T> val1 = val.reverse(); return val1.toLnStrm(); }
//
} // end of [public class Assign07_01{...}]
