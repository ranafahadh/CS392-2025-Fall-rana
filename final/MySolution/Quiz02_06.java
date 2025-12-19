//
// HX-2025-11-20: 50 points
// A partial implementation of
// randomized doubly linked binary search tree
// 30 points for reroot and 20 points for insert
//
import java.util.Random;
import java.util.function.ToIntBiFunction;
import Library.FnList.*;
import Library.FnTuple.*;

public class Quiz02_06<K,V> {
    Random rand = new Random();
    Node root = null;
    private ToIntBiFunction<K,K> cmp;

    public Quiz02_06(ToIntBiFunction<K,K> cmp0) { cmp = cmp0; }

    public class Node {
        K key;
        V val;
        int size;
        Node parent;
        Node lchild;
        Node rchild;
    }

    private int size(Node x) { return (x == null ? 0 : x.size); }

    private void fixSize(Node x) {
        if (((x != null))) { x.size = 1 + size(x.lchild) + size(x.rchild); } }

    private void rL(Node x) {
        Node y = x.rchild;
        if ((y == null)) return;
        x.rchild = y.lchild;
        if (((y.lchild != null))) y.lchild.parent = x;
        y.parent = x.parent;
        if ((x.parent == null)) { root = y; }
        else if (x.parent.lchild == x) { x.parent.lchild = y; }
        else { x.parent.rchild = y; }
        y.lchild = x; x.parent = y;
        fixSize(x); fixSize(y);
    }

    private void rR(Node x) {
        Node y = x.lchild;
        if (y == null) return;
        x.lchild = y.rchild;
        if ((y.rchild != null)) y.rchild.parent = x;
        y.parent = x.parent;
        if (((x.parent == null))) { root = y; }
        else if ((x.parent.lchild == x)) { x.parent.lchild = y; }
        else { x.parent.rchild = y; }
        y.rchild = x; x.parent = y;
        fixSize(x); fixSize(y);
    }

    private Node randomNode(Node x, int k) {
        int lsiz = size(x.lchild);
        if (((k < lsiz))) { return randomNode(x.lchild, k); }
        else if (k == lsiz) { return x; }
        else { return randomNode(x.rchild, (k - lsiz - 1)); }
    }

    public void reroot() {
        if ((root == null)) return;
        int k = rand.nextInt(root.size); Node x = randomNode(root, k);
        while ((x.parent != null)) {
            if ((x.parent.lchild == x)) { rR(x.parent); }
            else { rL(x.parent); } }
    }

    private int compare(K k1, K k2) { return cmp.applyAsInt(k1, k2); }

    public V get(K key) {
        Node cur = root;
        while (cur != null) {
            int c = compare(key, cur.key);
            if (c == 0) return cur.val;
            if (c < 0) cur = cur.lchild; else cur = cur.rchild;
        }
        return null;
    }

    public void put(K key, V val) {
        if (root == null) {
            root = new Node();
            root.key = key;
            root.val = val;
            root.size = 1;
            return;
        }
        Node cur = root;
        while (true) {
            int c = compare(key, cur.key);
            if (c == 0) { cur.val = val; return; }
            cur.size++;
            if (c < 0) {
                if (cur.lchild == null) {
                    Node n = new Node();
                    n.key = key; n.val = val; n.size = 1;
                    n.parent = cur; cur.lchild = n;
                    return;
                }
                cur = cur.lchild;
            } else {
                if (cur.rchild == null) {
                    Node n = new Node();
                    n.key = key; n.val = val; n.size = 1;
                    n.parent = cur; cur.rchild = n;
                    return;
                }
                cur = cur.rchild;
            }
        }
    }

    private FnList<FnTupl2<K,V>> toFnList0(Node x, FnList<FnTupl2<K,V>> acc) {
        if (x == null) return acc;
        acc = toFnList0(x.rchild, acc);
        acc = new FnList<>(new FnTupl2<>(x.key, x.val), acc);
        acc = toFnList0(x.lchild, acc);
        return acc;
    }

    public FnList<FnTupl2<K,V>> toFnList() {
        return toFnList0(root, new FnList<>());
    }

    public static void main(String[] args) {
        Quiz02_06<Integer,Integer> t = new Quiz02_06<>((x,y) -> x - y);
        t.put(5, 50); t.put(2, 20); t.put(8, 80); t.put(1, 10); t.put(3, 30);
        System.out.println("the root before reroot " + t.root.key);
        t.reroot();
        System.out.println("the roor after reroot   " + t.root.key);
        System.out.println("get(3) = " + t.get(3));
        t.put(3, 300);
        System.out.println("get(3) = " + t.get(3));
    }
}
