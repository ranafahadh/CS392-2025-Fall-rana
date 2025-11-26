//
// HX-2025-11-20: 50 points
// Some "hard" Sudoku puzzles can be
// found here: https://sudoku.com/hard/.
// This question is similar to Assign07_02.
// You are asked to use DFirstEnumerate and BFirstEnumerate
// in FnGtree to solve Sudoku puzzles. Your solution
// should be able to solve "hard" Sudoku puzzles effectively.
//
import Library.FnList.*;
import Library.LnStrm.*;
import Library.FnGtree.*;
import java.util.ArrayDeque;
//
class Sudoku implements FnGtree<Sudoku> {
    // Please find a way to represent a Sudoku puzzle
    private int[][] board; 
    public Sudoku() {
        board =  new int[9][9]; }
    public Sudoku( int[][] val) {
        board = new int[9][9];
        for (int r = 0; ( r < 9) ; r += 1) {
            for (int c = 0; (c < 9); c += 1) { board[r][c] = val[r][c]; }
        } }

    







    public static Sudoku fromString(String s) {
        Sudoku vall = new Sudoku();
        int k = 0;
        for ( int r = 0; r < 9; r += 1) {
            for ( int c = 0; (c < 9);  c += 1) {
                char valch = s.charAt(k++);
                if (valch == '.' || valch == '0' ) {
                    vall.board[r][c] = 0; } 
                else { vall.board[r][c] = valch - '0';  }
            } }
        return vall; }

    





    @Override
    public Sudoku value() { return this; }



    @Override
    public FnList<FnGtree<Sudoku>> children() {
        int[] val = findEmpty();
        FnList<FnGtree<Sudoku>> result = new FnList<>();
        if (val == null) { return result; }
        int valr = val[0]; int valc = val[1];
        
        for (int d = 1; d <= 9; d += 1) {
            if (isSafe(valr, valc, d)) {
                Sudoku valn = copyAndPlace( valr,  valc, d);
                result = new FnList<FnGtree<Sudoku>>( valn, result); } }
        return result; }


        


    private int[] findEmpty() {
        for ( int r = 0; r < 9; r += 1) {
            for (int  c = 0; ( c < 9) ; c += 1) {
                if (board[r][c] == 0) { return new int[]{r, c}; }  } }
        return null; }

    





    private boolean isSafe(int row, int col, int val) {

        for (int c = 0; (c < 9); c += 1) { if ( board[row][c] == val) return false; }
        for (int r = 0; (r < 9); r += 1) { if (board[r][col] == val) return false; }
        int br = (((row / 3))) * 3; int bc = ((col / 3)) * 3;
        for (int r = 0; (r < 3) ; r += 1) {
            for (int c = 0; ( c < 3); c += 1) {
                if (board[br + r][bc + c] == val) return false; } }
        return true; }







    private Sudoku copyAndPlace(int row, int col, int val) {
        Sudoku resultt = new Sudoku(this.board);
        resultt.board[row][col] = val;
        return resultt; }






  
    public boolean isSolved() { return (( findEmpty() == null)); }

    




    public void System$out$print() {
        for ( int r = 0; (r < 9); r += 1) {
            if (r % 3 == 0) {  System.out.println("+-------+-------+-------+"); }
            for (int c = 0; (( c < 9)); c += 1) {
                if ((c % 3 == 0)) { System.out.print("| "); }
                int v = board[r][c];
                if ((v == 0)) { System.out.print(". "); } 
                else { System.out.print(v + " "); }
        }
            System.out.println("|"); }
        System.out.println("+-------+-------+-------+"); }











    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for ( int r = 0; (r < 9); r += 1) {
            for ( int c = 0; ((c < 9)); c += 1) {
                sb.append( board[r][c]); } }
        return sb.toString(); }
} 

















//class
public class Quiz02_03 { 
    public static LnStrm<Sudoku> Soduku_dfs_solve(Sudoku puzzle) {
        ArrayDeque<Sudoku> stck = new ArrayDeque<>();
        FnList<Sudoku> val = new FnList<>();
        stck.push( puzzle);
        while (!stck.isEmpty()) {
            Sudoku valcur = stck.pop();
            if ( (valcur.isSolved())) {
                val =  new FnList<Sudoku>(valcur, val);
                continue; }
            FnList<FnGtree<Sudoku>> ch = valcur.children();
            FnList<FnGtree<Sudoku>> valt = ch;
            while ( !valt.nilq()) {
                FnGtree<Sudoku> node = valt.hd();
                Sudoku child = node.value();
                stck.push( child);
                valt = valt.tl(); } }
        val = val.reverse();
        return val.toLnStrm(); }

   




    public static LnStrm<Sudoku> Soduku_bfs_solve(Sudoku puzzle) {
        ArrayDeque<Sudoku> q = new ArrayDeque<>();
        FnList<Sudoku> val = new FnList<>();
        q.addLast( puzzle);
        while ((!q.isEmpty())) {
            Sudoku cr = q.removeFirst();
            if ( cr.isSolved()) {
                val = new FnList<Sudoku>(cr, val);
                continue; }
            FnList<FnGtree<Sudoku>> ch = cr.children();
            FnList<FnGtree<Sudoku>> valt = ch;
            while ((!valt.nilq())) {
                FnGtree<Sudoku> node = valt.hd();
                Sudoku child = node.value();
                q.addLast( child);
                valt =  valt.tl(); } }
        val = val.reverse();
        return val.toLnStrm(); }










    public static void main (String[] args) {
	// Please add minimal testing code for Sudoku_dfs_solve
	// Please add minimal testing code for Sudoku_bfs_solve
        
        String ps = "530070000" + "600195000" + "098000060" + "800060003" + "400803001" + "700020006" + "060000280" + "000419005" + "000080079";

        Sudoku p = Sudoku.fromString(ps);
        //orignal
         System.out.println("original ");
        p.System$out$print();
        //dfs
        System.out.println("dfs solutions ");
        LnStrm<Sudoku> dfs = Soduku_dfs_solve(p);
        dfs.foritm0(pz -> { pz.System$out$print(); });

        //bfs
        System.out.println( "bfs solutions ");
        LnStrm<Sudoku>  bfs = Soduku_bfs_solve(p);
        bfs.foritm0( pz -> { pz.System$out$print(); });

        return /*void*/;
    }
}
