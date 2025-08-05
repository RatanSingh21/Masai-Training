//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {

        // Constructor for the class -->> Declaration
        Employee emp_01 = new Employee();

        // Setting the values for the class
        emp_01.setEmployeeName("Ratan Singh");
        emp_01.setEmployeeDesignation("VP");
        emp_01.setEmployeeSalary(100000000);
        emp_01.setEmployeeDOB("29-02-1999");
        emp_01.setEmployeeAddress("Lamington Street, Chruchgate");

        // Calling the method
//        emp_01.displayEmployeeDetails();

        // Loop methods
        emp_01.printEmployeeNameNoOfTimes(5);

    }
}