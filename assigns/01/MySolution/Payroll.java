
import java.util.Arrays;
public class Payroll {
    public static final int INITIAL_MAXIMUM_SIZE = 1024;
    private Employee[] people;
    private int maximum_size;
    private int current_size;
    //everthing is tested
    public Payroll() {
	/* your code */
    // payout!

        this.maximum_size = Math.max(1, INITIAL_MAXIMUM_SIZE); this.people = new Employee[this.maximum_size]; this.current_size = 0; }
    
    public int size() {
        return current_size; }


    public void print() {
        for (int i = 0; i < current_size; i++) {
            Employee val = people[i]; System.out.println(i + " :) " + val.ID + "  " + val.name + "  " + val.salary);
        }
    }


    public void add_employee(Employee newbie) {
	/* your code */
    // not good
    //fixed it
        if ((newbie == null)) {
            throw new IllegalArgumentException("newbie cannot be null"); }
        ensureCapacity(current_size + 1);
        people[current_size] = newbie;
        current_size++; }





    public void remove_employee(int i) throws EmployeeIndexException {
	/* your code */
    // tested it 
    // good
    if ((i < 0 || i >= current_size)) {
            throw new EmployeeIndexException(); }
        int val = current_size - 1;
        people[i] = people[val];
        people[val] = null; current_size--; }
    




    public int find_employee(String name) throws EmployeeNotFoundException {
	/* your code */
    // tested the code! 
    //Good
    for (int i = 0 ; i <  current_size ; i++) {
        if ((people[i] != null && people[i].name != null && people[i].name.equals(name))) {
            return i; } }
    throw new EmployeeNotFoundException();
    }



    public void add_payroll(Payroll source) {
	/* your code */
    // gjood
    if ((source == null)) throw new IllegalArgumentException("null");
        ensureCapacity(this.current_size + source.current_size);
        for (int i = 0 ; i <  source.current_size ; i++) { this.people[this.current_size + i ] = source.people[i]; }
        this.current_size += source.current_size; }

    public void copy_payroll(Payroll source) {
	/* your code */
    // tested the code!
    //good!
        if (source == null) throw new IllegalArgumentException("source cannot be null");
            if ((this == source)) return; 
        this.maximum_size = Math.max(1, source.maximum_size); this.people = new Employee[this.maximum_size]; this.current_size = source.current_size;
        for (int i = 0; i < source.current_size; i++) { this.people[i] = source.people[i]; } }

    //helper 
    //tested!
    private void ensureCapacity(int val) {
        if (val <= maximum_size) return;
        int val1 = Math.max(1, maximum_size);
        while (val1 < val) val1 *= 2;
        people = Arrays.copyOf(people, val1);
        maximum_size = val1; }

    //private Employee people[];
    //private int maximum_size;
    //private int current_size;
    //...
}
