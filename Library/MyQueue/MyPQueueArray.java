package Library.MyPQueue;

import java.util.function.Consumer;
import java.util.function.BiConsumer;


public class MyPQueueArray<T extends Comparable<T>> extends MyPQueueBase<T> {

    private static final int DEFAULT_CAPACITY = 1024;

    private final T[] data;
    private int size;

    @SuppressWarnings("unchecked")
    public MyPQueueArray() {
        this.data = (T[]) new Comparable[DEFAULT_CAPACITY];
        this.size = 0;
    }

    @SuppressWarnings("unchecked")
    public MyPQueueArray(int capacity) {
        this.data = (T[]) new Comparable[capacity];
        this.size = 0;
    }

    // -------- status methods --------

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isFull() {
        return size >= data.length;
    }

    // -------- core priority queue operations --------

    @Override
    public T top$raw() {
        return data[0];
    }

    @Override
    public void enque$raw(T itm) {
        int i = size;
        data[i] = itm;
        size++;

        // sift up to maintain max-heap
        while (i > 0) {
            int parent = (i - 1) / 2;
            if (data[parent].compareTo(data[i]) >= 0) {
                break;
            }
            swap(i, parent);
            i = parent;
        }
    }

    @Override
    public T deque$raw() {
        T root = data[0];

        size--;
        if (size > 0) {
            data[0] = data[size];
            data[size] = null;
            siftDown(0);
        } else {
            data[0] = null;
        }

        return root;
    }

    // -------- helper methods --------

    private void swap(int i, int j) {
        T tmp = data[i];
        data[i] = data[j];
        data[j] = tmp;
    }

    private void siftDown(int i) {
        while (true) {
            int left = 2 * i + 1;
            int right = 2 * i + 2;

            if (left >= size) break;

            int biggerChild = left;
            if (right < size && data[right].compareTo(data[left]) > 0) {
                biggerChild = right;
            }

            if (data[i].compareTo(data[biggerChild]) >= 0) break;

            swap(i, biggerChild);
            i = biggerChild;
        }
    }
}
