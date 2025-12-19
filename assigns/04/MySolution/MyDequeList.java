import java.util.NoSuchElementException;
import java.util.function.BiConsumer;
import java.util.function.Consumer;











public class MyDequeList<T> {
    private static final class Node<E> {
        E x; Node<E> nx; Node<E> pv; Node(E x) { this.x = x; }
    }

    int nitm = 0; Node<T> frnt = null; Node<T> rear = null;

    public MyDequeList() { } public int size() { return nitm;  } public boolean isEmpty() { return nitm == 0;  }



    public T fpeek() { if (nitm == 0) throw new NoSuchElementException("empty!!"); return frnt.x; }




    public T rpeek() { if (nitm == 0) throw new NoSuchElementException("empty!"); return rear.x; }




    public void fenque(T itm) { Node<T> n = new Node<>(itm);
        if (frnt == null) {
            frnt = rear = n; } 
            else { n.nx = frnt; frnt.pv = n; frnt = n; } nitm += 1; }





    public void renque(T itm) {
        Node<T> val = new Node<>(itm);
        if (rear == null) { frnt = rear = val; } 
        else { rear.nx = val; val.pv = rear; rear = val; } nitm += 1; }





    public T fdeque() {
        if (nitm == 0) throw new NoSuchElementException("empty!");
        T x = frnt.x; Node<T> nxt = frnt.nx; frnt.nx = null;
        if (nxt != null) nxt.pv = null; frnt = nxt;
        if (frnt == null) rear = null; nitm -= 1; return x; }







    public T rdeque() {
        if (nitm == 0) throw new NoSuchElementException("empty!");
        T x = rear.x; Node<T> p = rear.pv;
        rear.pv = null;
        if (p != null) p.nx = null; rear = p;
        if (rear == null) frnt = null; nitm -= 1; return x; }


        

    public void foritm(Consumer<? super T> action) { for (Node<T> val = frnt; val != null; val = val.nx ) action.accept(val.x);  }




    public void iforitm(BiConsumer<Integer, ? super T> action) {
        int val = 0; for (Node<T> val1 = frnt; val1 != null; val1 = val1.nx) action.accept(val++, val1.x); }

    public void rforitm(Consumer<? super T> action) { for (Node<T> val = rear; (val != null); val = val.pv) action.accept(val.x); }



    public void irforitm(BiConsumer<Integer, ? super T> action) 
    { int val0 = 0; for (Node<T> val = rear; (val  != null); val = val.pv) action.accept(val0++, val.x); }



    public void System$out$print() {
        boolean val = true;
        for (Node<T> val1 =  frnt; (val1 !=  null); val1 =  val1.nx) {
        if (!val) System.out.print(" "); System.out.print(val1.x); val  = false; } System.out.println(); }
}
