//import Library.LnStrm.*;
//import java.util.Random;
//import java.util.function.Function;
//import java.util.function.Consumer;
//import java.util.function.Predicate;
//import java.util.function.BiFunction;
//import java.util.function.BiConsumer;
//import java.util.function.BiPredicate;
//import java.util.function.ToIntBiFunction;


import Library.LnStrm.*;
import java.util.PriorityQueue;
import java.util.function.ToIntBiFunction;

public class Assign06_01 {
    public static <T> LnStrm<T> mergeLnStrm(LnStrm<LnStrm<T>> fxss, ToIntBiFunction<T,T> cmpr) {
        final class Box {
            final T x;  final LnStrm<T> y; Box(T x, LnStrm<T> y) { this.x = x; this.y = y; } }
        final class  Ctx {
            LnStrm<LnStrm<T>> outer = fxss;
            final PriorityQueue<Box> q =
                new PriorityQueue<>((a, b) -> cmpr.applyAsInt(a.x, b.x)); }






        final Ctx  ctx  = new Ctx();
        java.util.function.Supplier<LnStcn<T>> sup = new java.util.function.Supplier<LnStcn<T>>() {
            boolean val0 = false;
            @Override public LnStcn<T> get() {
                if (!val0) { while (ctx.outer != null) {
                        LnStcn<LnStrm<T>> oc = ctx.outer.eval0();
                        if (oc == null) { ctx.outer = null; break; }
                        ctx.outer = oc.tail;
                        LnStrm<T> inner  = oc.head;
                        if (inner == null) continue;
                        LnStcn<T> ic =  inner.eval0();
                        if (ic != null) {
                            ctx.q.add(new  Box(ic.head, ic.tail)); } }
                    val0 = true; }
                if (ctx.q.isEmpty())  return null;
                Box b = ctx.q.poll();
                if (b.y != null) {
                    LnStcn<T>  nx = b.y.eval0();
                    if (nx != null) {
                        ctx.q.add(new Box(nx.head, nx.tail));
                    }
                } return new LnStcn<>(b.x, new LnStrm<>(this)); } }; return new LnStrm<>(sup); }






    private static <T> LnStrm<T> fromArray(T[] arr) {
        final class Ctx { int i = 0; }
        final Ctx c = new Ctx();
        java.util.function.Supplier<LnStcn<T>> sup = new java.util.function.Supplier<LnStcn<T>>() {
            @Override public LnStcn<T> get() {
                if (c.i >= arr.length) return null;
                T val = arr[c.i++];
                return new LnStcn<>(val, new LnStrm<>(this));
            } };
        return new LnStrm<>(sup); }







    @SafeVarargs
    private static <T> LnStrm<LnStrm<T>> fromStreams(LnStrm<T>... ss) {
        final class Ctx { int i = 0; }
        final Ctx c = new Ctx();
        java.util.function.Supplier<LnStcn<LnStrm<T>>> sup = new java.util.function.Supplier<LnStcn<LnStrm<T>>>() {
            @Override public LnStcn<LnStrm<T>> get() {
                if (c.i >= ss.length) return null;
                LnStrm<T> s = ss[c.i++];
                return new LnStcn<>(s, new LnStrm<>(this));
            } }; return new LnStrm<>(sup); }


} // end of [public class Assign06_01{...}]
