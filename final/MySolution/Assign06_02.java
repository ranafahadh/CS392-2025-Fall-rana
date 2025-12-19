import Library.LnStrm.*;
import Library.FnTuple.*;

import java.util.PriorityQueue;

//import java.util.Random;
//import java.util.function.Function;
//import java.util.function.Consumer;
//import java.util.function.Predicate;
//import java.util.function.BiFunction;
//import java.util.function.BiConsumer;
//import java.util.function.BiPredicate;
//import java.util.function.ToIntBiFunction;



public class Assign06_02 {

    public static LnStrm<FnTupl2<Integer,Integer>> cubeSumOrderedIntegerPairs() {

        final class Pair {
            final int x, y; final long sum;
            Pair(int x, int y) { this.x = x; this.y = y; this.sum = (long)x*x*x + (long)y*y*y; } }









        final class Ctx {
            int nextX = 1; 
            final PriorityQueue<Pair> pq = new PriorityQueue<>(
                ( a,b) -> {
                    int c = Long.compare(a.sum, b.sum); if (c != 0) return c;
                    c  = Integer.compare( a.x, b.x); if ( c != 0) return c;
                    return Integer.compare(a.y , b.y); } ); }
        
		
		
		
		final Ctx ctx = new Ctx();

        Runnable seedOne =  () ->  ctx.pq.add(new Pair(ctx.nextX, ctx.nextX++));
        java.util.function.Supplier<LnStcn<FnTupl2<Integer,Integer>>> sup =
          new java.util.function.Supplier<LnStcn<FnTupl2<Integer,Integer>>>() {
            @Override public LnStcn<FnTupl2<Integer,Integer>> get() {
                if (ctx.pq.isEmpty()) seedOne.run();
                if (ctx.pq.isEmpty()) return null;
                Pair p = ctx.pq.poll();
                ctx.pq.add(new Pair(p.x, p.y + 1)); 
                seedOne.run();                       
                FnTupl2<Integer,Integer> out = new FnTupl2<>(p.x, p.y);
                return new LnStcn<>(out, new LnStrm<>(this)); } }; return new LnStrm<>(sup); }

    
	
	
	
	
	public static LnStrm<Integer> ramanujanNumbers() {

    final class Pair {
        final int x, y;
        final long sum;
        Pair(int x,  int y) { this.x  = x; this.y  = y; this.sum =  (long)x*x*x + (long)y*y*y; } }
    final class Ctx {
        int nextX = 1; final PriorityQueue<Pair> pq = new PriorityQueue<>(
            (a,b) -> { int c = Long.compare(a.sum, b.sum); if (c != 0) return  c; c =  Integer.compare( a.x, b.x); if (c != 0)  return c; return Integer.compare(a.y, b.y); }
        ); }






    final Ctx ctx = new Ctx();

    Runnable seedOne = () -> ctx.pq.add(new Pair(ctx.nextX, ctx.nextX++));
    java.util.function.Supplier<Pair> popOne = () -> {
        if (ctx.pq.isEmpty())  seedOne.run(); if (ctx.pq.isEmpty()) return null;
        Pair p =  ctx.pq.poll();
        ctx.pq.add(  new Pair(  p.x, p.y + 1)); 
        seedOne.run();                      
        return p; };









    java.util.function.Supplier<LnStcn<Integer>> sup = new java.util.function.Supplier<LnStcn<Integer>>() {
        @Override public LnStcn<Integer> get() {
            while ( true) {
                Pair val = popOne.get();
                if ( val == null) return null;
                long s  = val.sum;
                int cnt  = 1;
                while (true) {
                    Pair pp  = ctx.pq.peek();
                    if (pp ==   null || pp.sum != s) break; popOne.get(); 
                    cnt++; }
                if (cnt >= 2) {
                    return new LnStcn<>((int)s, new LnStrm<>(this));
                } } } }; return new LnStrm<>(sup); }













   

    // passssed 
    public static void main(String[] args) {
        LnStrm<Integer> r = ramanujanNumbers();
        for (int  i = 0; i < 10; i++) { LnStcn<Integer> c = ( r == null) ? null : r.eval0();
            if (  c  == null) break; System.out.println(c.head); r = c.tail; } }
} // end of [public class Assign06_02{...}]
