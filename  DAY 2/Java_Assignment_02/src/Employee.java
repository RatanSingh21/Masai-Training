public class Employee {
    private String id;
    private String name;
    private String department;
    private TrainingModule TM;

    // Getter & Setter
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    // Constructor parameterized...
    public Employee(String id, String name, String department) {
        this.id = id;
        this.name = name;
        this.department = department;
    }

    public void showDetails(){
        System.out.println("The Employee Information are as follows: ");
        System.out.println("Name: " + name);
        System.out.println("Department: " + department);
    }

    // Overloading
    public static void calculateBonus(int base){
        System.out.println("Calculating bonus with only base: " + base);
    }

    public static void calculateBonus(int base, int rating){
        System.out.println("Calculating bonus with base: " + base + " and rating: " + rating);
    }

    // Validation
    public static boolean validateID(String id){
        if(id.length() != 5){
            System.out.println("ID not valid");
            return false;
        }

        for(char c: id.toCharArray()){
            if (!Character.isDigit(c)){
                System.out.println("ID not valid");
                return false;
            }
        }

        // all passes
        System.out.println("ID is valid");
        return true;
    }
}
