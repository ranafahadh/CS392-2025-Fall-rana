//
// HX-2025-11-20: 30 points
// (plus up to 20 bonus points)
// This is more of a theory problem
// than a programming one.
//
import Library.LnStrm.*;
//
public class Quiz02_04 {
    public class AVLnode {
	int key;
	AVLnode lchild;
	AVLnode rchild;
    }
    //
    // HX: 10 points for this one
    // HX: If your implementation only
    // visit each node in [avl] at most once,
    // then it will be rewarded with some bonus
    // points (up to 20 bonus points).
    // For instance, if you compute the size of
    // height of a tree, then you already visit
    // each node once.
    //
    public static boolean isAVL (AVLnode avl) {
	// HX: Please implement a function that
	// tests whether a given AVLnode is a valid
	// AVL tree. If it is unclear what an
	// AVL tree, you can readily find it on-line
	// Note that you are not asked to check if avl is
	// a binary search tree in this case.
	return ( hight(avl) != -1);  

    }

    // helper
    private static int hight(AVLnode n) {
	if ((n == null)) { return 0; }
	int hl = hight(n.lchild);
	if ((hl == -1)) return -1;
	int hr = hight(n.rchild);
	if (hr == -1) return -1;
	if (Math.abs( (hl - hr)) > 1) { return -1;  }
	return 1 + ((hl >= hr ? hl : hr)); }
    





    //
    // HX: 20 points
    // This is largely about understanding AVL trees.
    // Please explain BRIEFLY as to why the generated AVL is
    // of maximal height (not minimal height). Note that this
    // is different from what is asked in Quiz02_05.
    //
    public static boolean genAVLBST() {
	// Please genenerate a binary search RBT that
	// contains exactly 1 million keys: 0, 1, 2, ..., 999999
	// such that the height of this tree is maximal (that is,
	// as large as possible). What is this height? Please give
	// a brief explanation on your implementation strategy.
    // in order to make an AVL tree with 1 million keys, we want the tree to be as tall as we can make it.
    //the worst case it would keep one subtree with the hight h-1 and the other has the height h-2 at every node.
    // moreover, the number of nodes for height h is N(h) = N(h-1) + N(h-2) + 1. 
    //if we  grow a tree in this pattern until N(h) reaches 1,000,000 the height is about 27 which is the max 
    // height since any more balanced AVL tree with the same keys would have less height
    //
	return true;
    }







    public static void main (String[] args) {
	// Please add minimal testing code for isRBT()
	// Please add minimal testing code for genAVLBST()

	Quiz02_04 q = new Quiz02_04();
	AVLnode r = q.new AVLnode();
	r.key = 2;
	r.lchild = q.new AVLnode();
	r.lchild.key = 1;
	r.rchild = q.new AVLnode();
	r.rchild.key = 3;
	System.out.println("is balanced tree avl? " +  isAVL(r));
	AVLnode bad = q.new AVLnode();
	bad.key = 3;
	
    bad.lchild = q.new AVLnode();
	bad.lchild.key = 2;
	bad.lchild.lchild = q.new AVLnode();
	bad.lchild.lchild.key = 1;
	System.out.println( "is unbalanced tree is avl?  " + isAVL(bad));
	boolean genAVLBST = genAVLBST();
	System.out.println( genAVLBST);
	return /*void*/;
    }
}
