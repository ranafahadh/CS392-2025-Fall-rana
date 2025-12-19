
import java.util.function.Consumer;
import java.util.function.BiConsumer;
import java.util.ArrayDeque;



public class Assign04_01<T> extends MyQueueBase<T> {
//
    /*
      HX-2025-09-24:
      Please first copy your implementation of Assign03_03
      to this class.
     */

    /*
      The following four higher-order methods are declared
      in MyQueueBase<T>:
      
      public void foritm(Consumer<? super T> action);
      public void iforitm(BiConsumer<Integer, ? super T> action);
      public rforitm(Consumer<? super T> action);
      public irforitm(BiConsumer<Integer, ? super T> action);

      Please implement them for your two list based queue.
    */
    

    private final MyQueueList<T> q = new MyQueueList<>();

    public int size() { return q.size();      } public boolean isFull() { return q.isFull();  }
    public T top$raw() { return  q.top$raw();  } public T deque$raw() {  return q.deque$raw(); }
    public void enque$raw(T itm) {  q.enque$raw(itm); }   
    
    public void foritm(Consumer<? super T> action) {
        int val = size();
        for (int k = 0; k < val; k++) {
            T val1 = deque$raw();
            action.accept(val1);
            enque$raw(val1); } }



            

    public void iforitm(BiConsumer<Integer, ? super T> action) {
        int val = size();
        for (int k = 0; k < val; k++) 
         { T val1 = deque$raw();
            action.accept(k, val1); enque$raw(val1); } }






    public void rforitm(Consumer<? super T> action) {
        int val = size();
        ArrayDeque<T> val3 = new ArrayDeque<>(val);
        for (int k = 0; k < val; k++) {
            T val2 = deque$raw();
            val3.push(val2); enque$raw(val2); }
        while (!val3.isEmpty()) action.accept(val3.pop()); }





    public void irforitm(BiConsumer<Integer, ? super T> action) {
        int val = size(); ArrayDeque<T> val3 = new ArrayDeque<>(val);
        for (int k = 0; k < val; k++) {
            T val1 = deque$raw();
            val3.push(val1); enque$raw(val1);
        } int i = 0;
        while (!val3.isEmpty()) action.accept(i++, val3.pop()); }

} // end of [public class Assign04_01<T> extends MyQueueBase<T>{...}]

