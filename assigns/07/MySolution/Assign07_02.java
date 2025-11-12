import Library.LnStrm.*;
import Library.FnList.*;
import Library.FnGtree.*;

class UnsupportedOpr
    extends RuntimeException {
    String opr;
    public UnsupportedOpr(String opr) {
	this.opr = opr;
    }
}

abstract class Term {
    public String tag = "Term";
    public abstract double eval();
    // eval() returns the value of the term
}

class TermInt extends Term {
    public int val;
    public TermInt(int val) {
	this.tag = "TermInt"; this.val = val;
    }
    public double eval() { return val; }
}

class TermOpr extends Term {
    public String opr;
    public Term arg1, arg2;
    public TermOpr(String opr0, Term arg1, Term arg2) {
	this.tag = "TermOpr";
	this.opr = opr0; this.arg1 = arg1; this.arg2 = arg2;
    }
    public double eval() {
	switch (opr) {
	  case "+":
	      return arg1.eval() + arg2.eval();
	  case "-":
	      return arg1.eval() - arg2.eval();
	  case "*":
	      return arg1.eval() * arg2.eval();
	  case "/":
	      return arg1.eval() / arg2.eval();
	}
	throw new UnsupportedOpr(     opr     );
    }
}

public class Assign07_02 {
//
	static final double EPS = 1e-6;
    static boolean is24(double x){ return Math.abs(x-24.0)<EPS; }


    static class G24Node implements FnGtree<Term> {
        final Term[] xs; 
		G24Node(Term[] xs ){ this.xs  = xs;   }


        public Term value(){
            if(xs.length==1){ Term t = xs[0]; return is24( t.eval()) ?  t : null; }
            return null;    }

        public FnList<FnGtree<Term>> children(){
            int n = xs.length;  if( n<=1) return new FnList<>();
            FnList<FnGtree<Term>>  out = new FnList<>();
            for( int i=0;i<n;i++) for( int j=0; j<n;j++){
                if(( i==j)) continue;
                Term a =  xs[i], b = xs[j];
                Term[] r  = new Term[n-2]; int k=0; for( int p=0;p<n;p++) if(p!=i && p!=j) r[k++]=xs[p];
                out = new FnList<>(new G24Node( prepend( new TermOpr( "+",a,b), r)), out);
                out = new FnList<>( new G24Node( prepend(new TermOpr( "-",a,b), r)), out);
                out = new FnList<>( new G24Node(prepend(new TermOpr( "*",a,b), r)), out);
                if(Math.abs(b.eval())>EPS) out = new FnList<>(new G24Node(prepend(new TermOpr( "/" ,a,b), r)), out); }
            return out.reverse();   }



        static Term[] prepend(Term t, Term[] arr){ 
			Term[] y = new Term[ arr.length+1]; y[0]=t; System.arraycopy( arr,0,y,1,arr.length); return y; } }

    public LnStrm<Term> GameOf24_bfs_solve
	(int n1, int n2, int n3, int n4) {
	// Please find ALL the solutions of GameOf24
	// for the input n1, n2, n3, and n4
	// Each solution is represented as a Term
	// that evaluates to 24
	// Note that your solution should be based on
	// BFirstEnumerate implemented in Assign07_01

    Term[] xs =  new Term[] { new TermInt(n1), new TermInt( n2), new TermInt(n3), new TermInt( n4) };
        FnGtree<Term> root = new G24Node(xs); return Assign07_01.BFirstEnumerate(  root).filter0(t -> ( t != null) && is24(t.eval())); }


    public LnStrm<Term> GameOf24_dfs_solve
	(int n1, int n2, int n3, int n4) {
	// Please find ALL the solutions of GameOf24
	// for the input n1, n2, n3, and n4
	// Note that your solution should be based on
	// DFirstEnumerate implemented in Assign07_01
     Term[] xs = new Term[] { new TermInt( n1), new TermInt( n2), new TermInt(n3), new TermInt(n4) };
        FnGtree<Term> root = new G24Node(xs); return Assign07_01.DFirstEnumerate( root).filter0(t -> ( t != null) && is24(t.eval()));
    }
//
    // Please add minimal testing code for GameOf24_bfs_solve
    // Please add minimal testing code for GameOf24_dfs_solve
//
	public static void main(String[] args) {
        Assign07_02 a = new Assign07_02();
        System.out.println(" bfs for 3 3 8 8   ");  a.GameOf24_bfs_solve( 3, 3, 8, 8).foritm0(t -> System.out.println( helpp(t)));

        System.out.println(" dfs for 3 3 8 8   "); a.GameOf24_dfs_solve(3, 3, 8, 8).foritm0(t -> System.out.println( helpp(t)  ));
    }

    static String helpp(Term t) {
        if (t instanceof TermInt) {
            return Integer.toString(( (TermInt) t).val);   }
        if ( t instanceof TermOpr ) { TermOpr o = (TermOpr) t;  return helpp(o.arg1) + "  " + o.opr + "  " + helpp(o.arg2) ; } return "?"; }
} // end of [public class Assign07_02{...}]
