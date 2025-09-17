

public class TestPayroll {
    private static Employee emp(String name, int id, double salary) {
        Employee e = new Employee();
        e.name = name;
        e.ID = id;
        e.salary = salary;
        return e; }
    public static void main(String[] args) {
        Payroll p = new Payroll();
        p.add_employee(emp("EM1", 1, 100000));
        p.add_employee(emp("EM2", 2, 90000));
        p.add_employee(emp("EM3", 3, 80000));
        System.out.println( p.size());
        p.print();
        try {
            int idx = p.find_employee("EM2"); System.out.println("EM2 found at " + idx); } catch (EmployeeNotFoundException e) { System.out.println("ERR"); }
        try {
            p.find_employee("EM4"); System.out.println("ERR"); } catch (EmployeeNotFoundException e) { System.out.println("ERR");}
        try { int idx = p.find_employee("EM1"); p.remove_employee(idx); System.out.println("EM1 removed the  size is " + p.size()); } catch (Exception e) { System.out.println("ERR"); }
        try { p.remove_employee(999); System.out.println("ERR"); } catch (EmployeeIndexException e) { System.out.println("ERR"); }
        Payroll q = new Payroll(); q.add_employee(emp("X", 10, 50000)); q.add_employee(emp("Y", 11, 60000));
        Payroll r = new Payroll(); r.copy_payroll(q);
        r.print(); p.add_payroll(r);
        System.out.println("After add_payroll p "); p.print(); }
}
