public class Assign02_01 {
    /*
      HX-2025-02-13: 10 points
      The 'int' type in Java is for integers of some fixed precision.
      More precisely, each integer of the type int is represented as a sequence of bits
      of some fixed length. Please write a program that finds this length. Your program
      is expected return the correct answer instantly. Note that you can only use arithmetic
      operations here. In particular, NO BIT-WISE OPERATIONS ARE ALLOWED.
     */
    //ffirst one 
    // did all the testings and it works fine 
    //1/3
        static int bitLengthOfInt() {
            int cnt = 0; int val = 1;
            do { val += val;     
                cnt++;
            } while (val != 0);
            return cnt;}
        public static void main(String[] args) {
            System.out.println(bitLengthOfInt()); 
        }}
    

