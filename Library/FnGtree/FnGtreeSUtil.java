package Library.FnGtree;

import Library.FnList.*;
import Library.LnStrm.*;
import Library.MyPQueue.*;

import java.util.function.Supplier;

public class FnGtreeSUtil {
//
    public static <T> LnStrm<T> 
    PFirstEnumerate(FnGtree<T> root) {
    // HX-2025-12-02:
	// This method enumerates nodes according
	// to their priority numbers (obtained by
	// calling priority()

        final class Node implements Comparable<Node> {
            final FnGtree<T> tree;
            Node(FnGtree<T> t) { this.tree = t; }
            @Override
            public int compareTo(Node other) { return Integer.compare(other.tree.priority(), this.tree.priority()); } }



        final class Ctx { final MyPQueueArray<Node> pq = new MyPQueueArray<>(); }

        final Ctx ctx = new Ctx();
        ctx.pq.enque$raw(new Node(root));


        Supplier<LnStcn<T>> supplier = new Supplier<LnStcn<T>>() {
            @Override
            public LnStcn<T> get() {
                if (ctx.pq.isEmpty()) return new LnStcn<T>();
                Node n = ctx.pq.deque$raw();
                T v = n.tree.value();
                FnList<FnGtree<T>> kids = n.tree.children();
                while (kids.consq()) {
                    ctx.pq.enque$raw(new Node(kids.hd()));
                    kids = kids.tl(); }
                LnStrm<T> tail = new LnStrm<>(this);
                return new LnStcn<T>(v, tail); } };





                
        return new LnStrm<>(supplier); }





//
}// end of [public class FnGtreeSUtil{...}]
