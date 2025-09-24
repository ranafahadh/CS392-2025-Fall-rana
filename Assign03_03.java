public class Assign03_03<T> extends MyQueueBase<T> {

    int nitm = -1;
    FnList<T> frnt = null;
    FnList<T> rear = null;

    public Assign03_03() {
	nitm = 0;
	frnt = new FnList<T>();
	rear = new FnList<T>();
    }

    public int size() {
	return nitm;
    }

    public boolean isFull() {
	return false;
    }




    // it kept giving me error had to implemet 
    // did not have enough time to do all the testings
    // spend so much time trying to understant what i am supposed to do 
    public static final class FnList<E> {
        private final boolean emp;
        private final E head;
        private final FnList<E> ta;

        public FnList() {
            this.emp = true;
            this.h = null;
            this.ta = null;}
        
        
            private FnList(E h, FnList<E> t) {
                this.emp = false;
            this.h = h;
            this.ta = t; }

        public boolean emty() { return emp; }
        public E hd() { return h; }            
        public FnList<E> ta() { return ta; }    
        public FnList<E> con(E x) { return new FnList<>(x, this); }
        public FnList<E> rv() {
            FnList<E> val = new FnList<>(); FnList<E> val2 = this;
            while (!val2.emty()) {
                val = val.con(val2.hd());
                val2 = val2.ta(); } return val; } }

    private void norm() {
        if (frnt.emty() && !rear.emty()) {
            frnt = rear.rv();
            rear = new FnList<T>();
        } }

    public T top$raw() {
	// HX: Please implement
    norm();
    return frnt.hd(); }

    public T deque$raw() {
	// HX: Please implement
    norm();
        T x = frnt.hd();
        frnt = frnt.ta();
        nitm -= 1;
        return x; }


    public void enque$raw(T itm) {
	// HX: Please implement
    rear = rear.con(itm);
        nitm += 1; }
}
