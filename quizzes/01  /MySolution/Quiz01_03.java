//
// HX: 30 points
// This one may seem easy but can be time-consuming
// if you use a brute-force approach.
//
public class Quiz01_03 {
    public static
	<T extends Comparable<T>>
	T[] sort10WithNoRecursion
	(T x0, T x1, T x2, T x3, T x4, T x5, T x6, T x7, T x8, T x9) {
	// HX-2025-10-12:
	// Given 10 arguments,
	// please return an array of size 10 containing the
	// 10 arguments sorted according to the order implemented by
	// compareTo on T.
	// HX: No arrays, lists, etc.
	// HX: No recursion is allowed for this one
	// HX: No loops (either while-loop or for-loop) is allowed.
	// HX: Yes, you can use functions (but not recursive functions)
	// HX: Please do not try to write a HUGH if-then-else mumble jumble!
    @SuppressWarnings("unchecked")

        T[] vals =   (T[]) java.lang.reflect.Array.newInstance(x0.getClass(),  10 );
        vals[ 0]=x0;  vals[1]=x1; vals[2]=x2;  vals[3]=x3; vals[ 4]=x4; vals[5]=x5;  vals[6]=x6;  vals[ 7]=x7;    vals[8]=x8; vals[9]=x9;
        java.util.function.BiConsumer<Integer,Integer>  s = ( a, b) -> { if (vals[a].compareTo(vals[b]) > 0) { T tmp  = vals[a]; vals[a] = vals[b]; vals[b] =  tmp;  } };


        s.accept( 0,1);  s.accept( 1,2); s.accept(0,1);  s.accept(2,3); s.accept(1,2); s.accept( 0,1); s.accept( 3,4); s.accept(2,3); s.accept(1,2); s.accept(0,1);
        s.accept(4,5);  s.accept(3,4); s.accept(2,3);   s.accept( 1,2); s.accept( 0,1); s.accept(5,6); s.accept( 4,5); s.accept(3,4); s.accept(2,3); s.accept(1,2); 
		s.accept(0,1); s.accept(6,7); s.accept(5,6); s.accept( 4,5); s.accept(3,4); s.accept(2,3); s.accept(1,2); s.accept(0,1); s.accept(7,8); s.accept(6,7); 
		s.accept( 5 ,6);  s.accept( 4,5); s.accept(3,4); s.accept(2,3); s.accept(1,2); s.accept(0,1); s.accept(8,9); s.accept(7,8); s.accept(6,7); s.accept(5,6); s.accept(4,5); s.accept(3,4); s.accept(2,3); s.accept(1,2); s.accept(0,1);
		 return vals; }



    public static void main(String[] args) {
        Integer[] out = sort10WithNoRecursion( 9,5,3, 1,4 ,8, 2,6,7, 0 );
        for (Integer v : out) System.out.print(v +  "   "); System.out.println();  } }







        
   
