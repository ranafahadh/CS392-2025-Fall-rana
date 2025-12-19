import Library.FnList.*;
import Library.LnList.*;
import Library.FnTuple.*;
import Library.MyMap00.*;

import Library.LnStrm.*;
import java.util.function.BiConsumer;

public class Assign08_01<V> 
    implements MyMap00<String, V> {
    // HX-2025-11-12:
    // Please give an implementation of hash table
    // that uses separate chaining for handling collisions.
    private LnList<FnTupl2<String, FnList<V>>> table[];
    private int size;
    private static final int DEFAULT_CAPACITY = 101;

    @SuppressWarnings("unchecked")
    public Assign08_01() {
        this(DEFAULT_CAPACITY); }

    @SuppressWarnings("unchecked")
    public Assign08_01(int cap0) {
        table  = (LnList<FnTupl2<String, FnList<V>>>[]) new LnList[cap0]; for (int i = 0; i < cap0; i++) table[i] = new LnList<>();
        size  = 0; }









    private int idx(String key0) {
        int val0 = key0.hashCode() & 0x7fffffff;
        return val0 % table.length;  }



    //testedd itt
    private FnTupl2<String, FnList<V>> find(String k0) {
        LnList<FnTupl2<String, FnList<V>>> xs = table[idx(k0)];
        while ( !xs.nilq1()) {
            FnTupl2<String, FnList<V>> p0 =    xs.hd1();   if ( p0.sub0.equals(k0)) return p0;  xs = xs.tl1(); }
        return null; }







    //tested it
    private static <T> LnList<T> rev(LnList<T> xs0) {
        LnList<T> ac = new LnList<>();
        while (!xs0.nilq1())  {
            ac = new LnList<>(xs0.hd1(), ac);
            xs0 = xs0.tl1(); }   return ac   ; }














    //tested it 
    private boolean ins(String k0,  V v0) {
        int a0 =  idx(k0);
        LnList<FnTupl2<String, FnList<V>>>  oldd = table[a0];
        LnList<FnTupl2<String, FnList<V>>>  buil =  new LnList<>();
        boolean hit0 = false;
        LnList<FnTupl2<String, FnList<V>>>  t0   = oldd;

         while (!t0.nilq1()) {
            FnTupl2<String, FnList<V>> e0 = t0.hd1();
            String  k1 = e0.sub0; FnList<V> vs0 = e0.sub1;

            if (k1.equals(k0)) {
                FnList<V> newVs =  new FnList<>(v0, vs0); FnTupl2<String, FnList<V>> ne0 = new FnTupl2<>(k0, newVs);
                buil = new LnList<>(ne0, buil); hit0 = true;
            } else { buil = new LnList<>(e0, buil); }
            t0 = t0.tl1(); }

        if (!hit0) {
            FnList<V> one0 =  new FnList<>( v0,  new FnList<>());
            FnTupl2<String,  FnList<V>> en0  = new FnTupl2<>(k0, one0);
            buil = new LnList<>( en0, buil); size++; }
        table[a0] = rev( buil); return true;}



















    private FnList<V> rem( String k0, boolean throw0) {
        int a0 = idx(k0);
        LnList<FnTupl2<String, FnList<V>>> old0 = table[a0];
        LnList<FnTupl2<String, FnList<V>>> built0 = new LnList<>();
        FnList<V> out0 =   null;
        boolean hit0 = false;

        LnList<FnTupl2<String, FnList<V>>> t0 = old0;
        while (!t0.nilq1()) {
            FnTupl2<String, FnList<V>> e0 =  t0.hd1();
            String k1 =  e0.sub0;
            FnList<V> vs0 = e0.sub1;
            if (k1.equals( k0)) {
                if (!hit0) {
                    out0 = vs0;
                    hit0 = true; } } else {  built0 = new LnList<>(  e0, built0); } t0 = t0.tl1(); }

        if (!hit0) {
            if (throw0) throw new RuntimeException(" not found" +    k0); return null; } table[a0] = rev(built0); size--; return out0; }








    public int size() {
        return size; }

    public boolean isFull() {
        return false;  }

    public boolean isEmpty() {
        return size == 0;}









    public LnStrm<FnTupl2<String, FnList<V>>> strmize() {
        FnList<FnTupl2<String, FnList<V>>> acc0 = new FnList<>();
        for (int i = 0; i <     table.length; i++) {
            LnList<FnTupl2<String, FnList<V>>> t0 =   table[i];
            while (!t0.nilq1()) {
                acc0 = new FnList<>(t0.hd1(), acc0);
                t0 = t0.tl1(); } }     return acc0.toLnStrm(); }






    public FnList<V> search$raw(String k0) {
        FnTupl2<String, FnList<V>> p0 =  find(k0);
        if (p0 == null ) throw new RuntimeException("not found: "   + k0); return p0.sub1; }






    public FnList<V> search$exn(String k0) {
        FnTupl2<String, FnList<V>> p0 =   find(k0);
        if (p0 == null) throw new RuntimeException( "not found " + k0); return p0.sub1; }





    public FnList<V> search$opt(String  k0) {
        FnTupl2<String, FnList<V>> p0 = find(k0);
        return p0 == null ? null : p0.sub1; }

    public void insert$raw(String k0,  V v0) {
        ins(k0, v0); }

    public void insert$exn(String k0, V v0) {
        boolean ok0 = ins(k0, v0);
        if (!ok0) throw new RuntimeException("filed" ); }

    public boolean insert$opt(String k0, V v0) {
        return ins(k0, v0); }

    public FnList<V> remove$raw(String  k0) {
        FnList<V> v0 = rem(k0, false);
        if (v0 == null) throw new RuntimeException( " not found " + k0); return v0; }




    public FnList<V> remove$exn(String k0) {
        return rem(k0,  true); }

    public FnList<V> remove$opt(String  k0) {
        return rem(k0, false); }











    public void foritm(BiConsumer<? super String, ? super V> ww) {
        for (int i = 0; i < table.length; i++) {
            LnList<FnTupl2<String, FnList<V>>> t0  = table[i];
            while (!t0.nilq1()) {
                FnTupl2<String, FnList<V>> p0 = t0.hd1();
                String k0  = p0.sub0; FnList<V> vs0 = p0.sub1;
                FnList<V> vv =  vs0;
                while (!vv.nilq()) {
                    ww.accept(k0, vv.hd()); vv =  vv.tl(); } t0 = t0.tl1(); } } } }
