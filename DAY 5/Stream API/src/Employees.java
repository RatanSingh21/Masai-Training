public class Employees {

    private String name;
    private String role;
    private int salary;

    // Getter and Setter

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    // Constructor
    public Employees(String name, String role) {
        this.name = name;
        this.role = role;
    }

    public Employees(int salary) {
        this.salary = salary;
    }

    public Employees(String name, String role, int salary) {
        this.name = name;
        this.role = role;
        this.salary = salary;
    }

    // toString method
    @Override
    public String toString() {
        return "Employees{" +
                "name='" + name + '\'' +
                ", role='" + role + '\'' +
                ", salary=" + salary +
                '}';
    }

}
