import Library.FnList.*;
import Library.LnList.*;
import Library.FnTuple.*;
import Library.MyMap00.*;
import Library.LnStrm.*;
import java.util.function.BiConsumer;

public class Assign08_02<V> 
    implements MyMap00<String, V> {
    // HX-2025-11-12:
    // Please give an implementation of hash table
    // based on open addressing. The probing strategy
    // chosen for handling collisions is quadratic probing.
    private FnTupl2<String, FnList<V>> table[];

    private byte[] state0;
    private int size0;
    private static final int DEFAULT_CAPACITY = 101;
    private static final byte EMPTY = 0;
    private static final byte OCC = 1;
    private static final byte DEL = 2;



    @SuppressWarnings("unchecked")
    public Assign08_02() {
        this(DEFAULT_CAPACITY); }

    @SuppressWarnings("unchecked")
    public Assign08_02(int cap) {
        table = (FnTupl2<String, FnList<V>>[]) new FnTupl2[cap ];
        state0 = new byte[cap];
        size0 = 0; }







    private int idx(String k) {
        int val = k.hashCode() & 0x7fffffff;
        return val % table.length; }







    private int findIdx(String k) {
        int m = table.length;
        int h = idx(k);
        for (int i = 0; i < m; i++) {
            int a = (h + i * i) % m;
            byte st = state0[a];
            if (st == EMPTY) return -1;
            if (st == OCC) {
                FnTupl2<String, FnList<V>> e= table[a];
                if (e.sub0.equals(k)) return a;
            } } return -1; }








    private int findSlot(String k) {
        int m = table.length;
        int h = idx(k);
        int de = -1;
        for (int i = 0; i < m; i++) {
            int a = (h + i * i) % m;
            byte st = state0[a];

            if (st == OCC) {
                FnTupl2<String, FnList<V>> e = table[ a];
                if (e.sub0.equals(k)) return a;
            } else if (st == DEL) {
                if (de < 0) de = a;
            } else if (st == EMPTY) {
                return (de >= 0 ? de : a); }
        } return de; }








    private boolean ins(String k0, V v0) {
        int ix0 = findIdx(k0);
        if (ix0 >= 0) {
            FnTupl2<String, FnList<V>> e0 = table[ix0];
            FnList<V> vs0 = e0.sub1;
            e0.sub1 = new FnList<>(v0, vs0);
            return true; }

        int slot = findSlot(k0);
        if (slot < 0) return false;

        FnList<V> one = new FnList<>( v0, new FnList<>());
        FnTupl2<String, FnList<V>> ne = new  FnTupl2<>(k0, one);
        table[slot] = ne;
        if (state0[slot] !=  OCC) {
            state0[slot] =  OCC;
            size0++; } return true; }









    private FnList<V> rem(String k0, boolean thr) {
        int ix = findIdx(k0);
        if (ix < 0) {
            if (thr) throw new RuntimeException("not found " + k0);
            return null; }

        FnList<V> ot = table[ix].sub1;
        table[ix] = null; state0[ ix]  = DEL;
        size0--;
        return ot; }










    public int size() {
        return size0; }

    public boolean isFull() {
        return size0 == table.length; }

    public boolean isEmpty() {
        return size0 == 0; }








    public LnStrm<FnTupl2<String,  FnList<V>>> strmize() {
        FnList<FnTupl2<String, FnList<V>>> ac = new FnList<>();
        for (int i = 0; i  < table.length; i++) {
            if (state0[i] == OCC) {
                FnTupl2<String, FnList<V>> e =  table[i];
                ac = new FnList<>(e, ac);  } } return ac.toLnStrm(); }









    public FnList<V> search$raw(String k0) {
        int ix = findIdx(k0);
        if (ix < 0) throw new RuntimeException(" not found " + k0);
        return table[ix].sub1; }

    public FnList<V> search$exn(String k0) {
        int ix= findIdx(k0);
        if (ix < 0) throw new RuntimeException(" not found " + k0);
        return table[ix].sub1; }

    public FnList<V> search$opt(String k0) {
        int ix= findIdx(k0);
        return (ix < 0 ? null : table[ix].sub1); }






    public void insert$raw(String k0, V v0) {
        boolean ok = ins( k0, v0);
        if (!ok) throw new RuntimeException( "full for " + k0); }

    public void insert$exn(String k0, V v0) {
        boolean ok = ins(k0, v0);
        if (!ok) throw new RuntimeException(  "insert failed " + k0); }

    public boolean insert$opt(String k0, V v0) {
        return ins(k0, v0); }







    public FnList<V> remove$raw(String k0) {
        FnList<V> ot = rem(k0, false);
        if (ot == null) throw new RuntimeException( "not found " + k0);
        return ot; }

    public FnList<V> remove$exn(String k0) {
        return rem(k0,   true); }






    public FnList<V> remove$opt(String k0) {
        return rem( k0, false); }









    public void foritm(BiConsumer<? super String, ? super V> ww) {
        for (int    i = 0; i <  table.length;  i++) {
            if (state0[i] == OCC) {
                FnTupl2<String, FnList<V>> e =  table[i];
                String k0 =  e.sub0;
                FnList<V> vs =  e.sub1;
                FnList<V> t = vs;
                while (!t.nilq()) {
                    ww.accept(k0, t.hd());
                    t = t.tl(); } } } }
                
                
                

}
