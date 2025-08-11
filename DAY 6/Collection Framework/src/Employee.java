public class Employee implements Comparable{

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

    // toString

    @Override
    public String toString() {
        return "Employee{" +
                "name='" + name + '\'' +
                ", ID='" + ID + '\'' +
                ", salary=" + salary +
                ", department='" + department + '\'' +
                '}';
    }

    @Override
    public int compareTo(Object o) {
        return 0;
    }
}

