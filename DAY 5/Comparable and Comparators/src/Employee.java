public class Employee implements Comparable {

    private String name;
    private String ID;
    private double salary;
    private String department;

    // Getter and Setter
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    // Constructor
    public Employee(String name, String ID, double salary, String department) {
        this.name = name;
        this.ID = ID;
        this.salary = salary;
        this.department = department;
    }

    // TO string

    @Override
    public String toString() {
        return "Employee{" +
                "name='" + name + '\'' +
                ", ID='" + ID + '\'' +
                ", salary=" + salary +
                ", department='" + department + '\'' +
                '}';
    }

    // Using comparator for comparing objects
    @Override
    public int compareTo(Object O){
        Employee emp = (Employee) O;
        if (this.salary > emp.salary){
            return 1;
        } else if(this.salary < emp.salary){
            return -1;
        } else {
            return 0;
        }
    }
}
