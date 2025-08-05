public class Employee {

    private String employeeName;
    private String employeeDesignation;
    private long employeeSalary;
    private String employeeDOB;
    private String employeeAddress;

    //Getters and Setters

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public String getEmployeeDesignation() {
        return employeeDesignation;
    }

    public void setEmployeeDesignation(String employeeDesignation) {
        this.employeeDesignation = employeeDesignation;
    }

    public long getEmployeeSalary() {
        return employeeSalary;
    }

    public void setEmployeeSalary(long employeeSalary) {
        this.employeeSalary = employeeSalary;
    }

    public String getEmployeeDOB() {
        return employeeDOB;
    }

    public void setEmployeeDOB(String employeeDOB) {
        this.employeeDOB = employeeDOB;
    }

    public String getEmployeeAddress() {
        return employeeAddress;
    }

    public void setEmployeeAddress(String employeeAddress) {
        this.employeeAddress = employeeAddress;
    }

    public void displayEmployeeDetails() {
        System.out.println("The details of the Employees are: ");

        System.out.println("Name: " + employeeName);
        System.out.println("Designation: " + employeeDesignation);
        System.out.println("Salary: " + employeeSalary);
        System.out.println("DOB: " + employeeDOB);
        System.out.println("Addr: " + employeeAddress);
    }

    public void printEmployeeNameNoOfTimes(int number) {

        // while loop
//        int n = 1;
//        while (n <= number) {
//            System.out.println(employeeName);
//            n++;
//        }

        // For loop
//        for(int i = 1; i<= number; i++){
//            System.out.println(employeeName);
//        }

        // do while loop
//        int i= 1;
//        do{
//            System.out.println(employeeName);
//            i++;
//        }while(i <= number);
    }
}
