package Library.MyPQueue;

import java.util.function.Consumer;
import java.util.function.BiConsumer;


public class MyPQueueArray<T extends Comparable<T>> extends MyPQueueBase<T> {

    private static final int CAPACITY = 100000;
    private final T[] data;
    private int size;








    @SuppressWarnings("unchecked")
    public MyPQueueArray() {
        this.data = (T[]) new Comparable[CAPACITY];
        this.size = 0; }














    @SuppressWarnings("unchecked")
    public MyPQueueArray(int capacity) {
        this.data = (T[]) new Comparable[capacity];
        this.size = 0; }

   









    @Override
    public int size() {
        return size; }




    @Override
    public boolean isFull() {
        return size >= data.length; }

   

    @Override
    public T top$raw() {
        return data[0]; }









    @Override
    public void enque$raw(T itm) {
        int i = size;
        data[i] = itm;
        size++;
        while ( i > 0) {
            int parent = ( i - 1) / 2;
            if (data[parent].compareTo( data[i]) >= 0) {
                break; }
            swap(i, parent);
            i = parent; } }






    @Override
    public T deque$raw() {
        T root = data[0];
        size--;
        if (size > 0) {
            data[0] = data[size];
            data[size] = null;
            siftDown(0);
        } else {
            data[0] = null; }
        return root; }

    






    //helper

    private void swap(int i, int j) {
        T val = data[i];
        data[i] = data[j];
        data[j] = val; }











    private void siftDown(int i) {
        while (true) {
            int l = 2 * i + 1;
            int r = 2 * i + 2;
            if (l >= size) break;
            int result = l;
            if (r < size && data[r].compareTo(data[l]) > 0) {
                result = r; }

            if (data[i].compareTo(data[result]) >= 0) break;

            swap(i, result);
            i = result; } }
}
