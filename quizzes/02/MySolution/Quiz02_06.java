//
// HX-2025-11-20: 50 points
// A partial implementation of
// randomized doubly linked binary search tree
// 30 points for reroot and 20 points for insert
//
import java.util.Random;
public class Quiz02_06 {
    Random rand = new Random();
	Node root = null;
    public class Node {
	int key; // key stored in the node
	int size; // size of the tree rooted as the node
	Node parent; // parent of the node
	Node lchild; // left-child of the node
	Node rchild; // right-child of the node
    }




    private int size(Node x) { return (x == null ? 0 : x.size); }





    private void fixSize(Node x) {
        if (((x != null))) { x.size = 1 + size(x.lchild) + size(x.rchild); } }







		//helper
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









	//helper
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
	// HX-2025-11-20: 30 points
	// [reroot] picks a node RANDOMLY and
	// uses rotations to turn this picked node
	// into the root of a new binary search tree
	// (containing the same set of keys)
        if ((root == null)) return;
        int k = rand.nextInt(root.size); Node x = randomNode(root, k);
        while ((x.parent != null)) {
            if ((x.parent.lchild == x)) {
                rR(x.parent); } 
			else { rL(x.parent);} } }



	public boolean insert(int key) {
	// HX-2025-11-20: 20 points
	// If key is in the tree stored at [root],
	// [insert] does no nothing and just returns false
	// If key is not in the tree stored at [root],
	// the key is inserted as a leaf node and the new
	// tree is still a binary search tree and [insert]
	// returns true (to indicate insertion is done).
        if (root == null) {
            root = new Node();
            root.key = key;
            root.size = 1;
            return true; }


        Node valcur = root;
        while (true) { if ((key == valcur.key)) return false; 
            valcur.size++;  
            if ((( key < valcur.key))) {
                if ((valcur.lchild == null)) {
                    Node n =  new Node();
                    n.key =  key;
                    n.size = 1;
                    n.parent = valcur;
                    valcur.lchild = n;
                    return true; }
                valcur = valcur.lchild; } 
				else { if ((valcur.rchild == null)) {
                    Node n = new Node();
                    n.key = key;
                    n.size = 1;
                     n.parent = valcur;
                    valcur.rchild = n;
                    return true; }
                valcur = valcur.rchild; } }
    }





    
    public static void main(String[] args) {
	// Please add minimal testing code for reroot()
	// Please add minimal testing code for insert()
        Quiz02_06 t = new Quiz02_06();

        t.insert(5); t.insert(2); t.insert(8); t.insert(1); t.insert(3);
        System.out.println("the root before reroot " + t.root.key);

        t.reroot();
        System.out.println("the roor after reroot   " + t.root.key);
        System.out.println( "inserting existing key?? " + t.insert(3));
        System.out.println("insrting new key?? " + t.insert(7));
    }
}
