import java.util.LinkedList;
public class Assign03_02 {
    static boolean balencedq(String text) {
	//
	// There are only '(', ')', '[', ']', '{', and '}'
	// appearing in [text]. This method should return
	// true if and only if the parentheses/brackets/braces
	// in [text] are balenced.
	// Your solution must make proper use of MyStack!
	
    // please take a look at my code file MyStack.java, i have implmented it since we are required to do MyStack!
	MyStack<Character> s = new MyStack<>();  // using mystack!!
        for (int i = 0 ; i <  text.length(); i++) {  // the loop
            char val = text.charAt(i);
            if (helperone(val)) {
                s.push(val); }
            else if (helpertwo(val)) {
                if (s.isEmpty()) return false; 
                char op = s.pop();
                if (!helperthree(op, val)) return false;}} 
				return s.isEmpty(); }


	// helpers to cheach the opening and the closing and if it is matching
	private static boolean helperone(char x) {
    		return x  == '('  || x  == '[' ||  x ==  '{'; }

    private static boolean helpertwo(char x) {
        return x  == ')' || x  ==  ']' ||  x  == '}'; }



    private static boolean helperthree(char x, char y) {
        return (x  == '(' &&  y  == ')') || (x   == '[' &&   y == ']') || (x == '{' &&  y == '}'); }



    public static void main(String[] argv) {
	// Please write some testing code for your 'balencedq"
	// did all the testings and all goodd!!

	String[] tst = { "({()[{}]})",  "({()[{()]})", "((()))",  "([)]",  "([]{})" ,  "{[(])}" };
        for (String expr : tst) { System.out.println(balencedq(expr)); } }
}
