import Library.LnStrm.*;
import java.util.Random;
import java.util.function.Function;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.function.BiFunction;
import java.util.function.BiConsumer;
import java.util.function.BiPredicate;
import java.util.function.ToIntBiFunction;


public class Assign06_01 {

    private static <T> LnStrm<T> wrap(LnStcn<T> c) {
        return new LnStrm<>(() -> c); }

    private static <T>
    LnStrm<T> fuse(LnStrm<T> a , LnStrm<T>  b , ToIntBiFunction<T,T>  cmp) {
        return new LnStrm<>(() -> {
            LnStcn<T>  ca =  a.eval0(); LnStcn<T> cb = b.eval0();
            if (ca.nilq()) return cb;
            if ( cb.nilq()) return ca;
            T  x = ca.head,  y = cb.head;
            if (cmp.applyAsInt( x ,  y ) <=  0 ) {
                return new LnStcn<>(x, fuse(ca.tail, wrap(cb), cmp)); } else { return new LnStcn<>(y, fuse(wrap(ca), cb.tail, cmp)); }  }); }





    public static <T>
    LnStrm<T> mergeLnStrm(LnStrm<LnStrm<T>> fxss, ToIntBiFunction<T,T> cmpr) {
        LnStcn<LnStrm<T>> val = fxss.eval0();
        if ( val.nilq())  return new  LnStrm<>(LnStcn::new);
        LnStrm<T> f = val.head;
        LnStrm<LnStrm<T>>  r =  val.tail;
        return fuse(f, mergeLnStrm( r ,  cmpr ),  cmpr ); }




// did some testings 

}




