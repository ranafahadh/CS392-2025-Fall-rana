import Library.FnList.*;
import Library.FnTuple.*;
import Library.LnStrm.*;
import Library.FnGtree.*;

public class Assign09_02 {
    // HX-2025-12-02:
    // Please use Warnsdorf's rule to
    // search for knight's tours on a chess board
    // of dimension (chessBoardSize x chessBoardSize)
    // Your search should be based on the PFirstEnumerate
    // (See Code/FnGtree/FnGtreeSUtil.java)

    public static LnStrm<FnList<FnTupl2<Integer,Integer>>> genKnightsTours(int chessBoardSize) {
    // I expect you to find some knight's tours for
	// a board of dimension 8x8; there will be bonus
	// points for handling larger boards.

        FnTupl2<Integer,Integer> startPos = new FnTupl2<>(0, 0);
        FnList<FnTupl2<Integer,Integer>> emptyPath = new FnList<>();
        FnList<FnTupl2<Integer,Integer>> startPath = new FnList<>(startPos, emptyPath);
        FnGtree<FnList<FnTupl2<Integer,Integer>>> root = makeNode(startPath, chessBoardSize);
        LnStrm<FnList<FnTupl2<Integer,Integer>>> allPaths = FnGtreeSUtil.PFirstEnumerate(root);
        final int targetLen = chessBoardSize * chessBoardSize;
        return allPaths.filter0(path -> path.length() == targetLen); }
    // Please write minimal testing code for [genKnightsTours]




    private static FnGtree<FnList<FnTupl2<Integer,Integer>>>
    makeNode(FnList<FnTupl2<Integer,Integer>> path, int n) {
        return new FnGtree<FnList<FnTupl2<Integer,Integer>>>() {
            
            
            @Override
            public FnList<FnTupl2<Integer,Integer>> value() {
                return path; }




            @Override
            public int priority() {
                FnTupl2<Integer,Integer> cur = path.hd();
                int x = cur.sub0;
                int y = cur.sub1;
                return countOnwardMoves(x, y, path, n); }





            @Override
            public FnList<FnGtree<FnList<FnTupl2<Integer,Integer>>>> children() {
                FnTupl2<Integer,Integer> cur = path.hd();
                int x = cur.sub0;
                int y = cur.sub1;
                FnList<FnGtree<FnList<FnTupl2<Integer,Integer>>>> kids = new FnList<>();
                int[][] moves = {
                        { 2, 1 }, { 1, 2 }, { -1, 2 }, { -2, 1 }, { -2, -1 }, { -1, -2 }, { 1, -2 }, { 2, -1 } };
                for (int i = 0; i < moves.length; i++) {
                    int nx = x + moves[i][0];
                    int ny = y + moves[i][1];
                    if (inBounds(nx, ny, n) &&
                        !contains(path, nx, ny)) {
                        FnTupl2<Integer,Integer> nextPos = new FnTupl2<>(nx, ny);
                        FnList<FnTupl2<Integer,Integer>> newPath = new FnList<>(nextPos, path);
                        FnGtree<FnList<FnTupl2<Integer,Integer>>> child = makeNode(newPath, n);
                        kids = new FnList<>(child, kids); } } return kids; } }; }








                        

    private static boolean inBounds(int x, int y, int n) {
        return (x >= 0 && x < n && y >= 0 && y < n); }






    private static boolean
    contains(FnList<FnTupl2<Integer,Integer>> path, int x, int y) {
        FnList<FnTupl2<Integer,Integer>> xs = path;
        while (xs.consq()) {
            FnTupl2<Integer,Integer> p = xs.hd();
            int px = p.sub0;
            int py = p.sub1;
            if (px == x && py == y) return true;
            xs = xs.tl(); } return false; }












    private static int
    countOnwardMoves(int x, int y, FnList<FnTupl2<Integer,Integer>> path, int n) {

        int[][] moves = { { 2, 1 }, { 1, 2 }, { -1, 2 }, { -2, 1 }, { -2, -1 }, { -1, -2 }, { 1, -2 }, { 2, -1 } };
        int count = 0;
        for (int i = 0; i < moves.length; i++) {
            int nx = x + moves[i][0];
            int ny = y + moves[i][1];
            if (inBounds(nx, ny, n) && !contains(path, nx, ny)) {
                count++;  } } return count; }











    public static void main(String[] args) {
    LnStrm<FnList<FnTupl2<Integer,Integer>>> tours = genKnightsTours(5);

    final int MAX = 3;
    final int[] count = {0};

    tours.foritm0(path -> {
        if (count[0] >= MAX) {
            System.exit(0);  //stoping after 3 tours since it was running for too long 
            }


        System.out.println("tour length is    " + path.length());
        path.System$out$print(); System.out.println("\n"); count[0]++;
    });
}



}
