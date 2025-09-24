// I have tried to call class Mystack but it does not exists and since we are required to use MyStack this is my version of it
// i tried my best :)

import java.util.LinkedList;
public class MyStack<T> {
    private LinkedList<T> val = new LinkedList<>();
    public void push(T item) {
        val.addFirst(item); }
        
    public T pop() {
        if (val.isEmpty()) {
            throw new RuntimeException("itis empty"); }
        return val.removeFirst(); }

    public boolean isEmpty() {
        return val.isEmpty();} }
