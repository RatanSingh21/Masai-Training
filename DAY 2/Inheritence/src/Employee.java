public class Employee extends Person{
    private int empcode;
    private String designation;
    private float salary;



    // Getter and Setter
    public int getEmpcode() {
        return empcode;
    }

    public void setEmpcode(int empcode) {
        this.empcode = empcode;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public float getSalary() {
        return salary;
    }

    public void setSalary(float salary) {
        this.salary = salary;
    }

    // Constructor
    public Employee(String name, int age, int empcode, String designation, float salary) {
        super(name, age);
        this.empcode = empcode;
        this.designation = designation;
        this.salary = salary;
    }

    public void applyforleave(){
        System.out.println("Applying for leave.. Please grant Shibu...");
    }


    public void displayinfo() {
        System.out.println("The display information of person as an employee...");
        System.out.println("Employee: " + empcode);
        System.out.println("Designation: " + designation);
        System.out.println("Salary: " +salary);
    }
}
