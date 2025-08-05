public class EmployeeOnboardingSystem {

    private String employeeName;
    private int employeeAge;
    private String employeeCity;
    private float employeeJoiningPercentage;
    private char employeePerformance;
    final String COMPANY_NAME = "HDFC_LIFE";

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public int getEmployeeAge() {
        return employeeAge;
    }

    public void setEmployeeAge(int employeeAge) {
        this.employeeAge = employeeAge;
    }

    public String getEmployeeCity() {
        return employeeCity;
    }

    public void setEmployeeCity(String employeeCity) {
        this.employeeCity = employeeCity;
    }

    public float getEmployeeJoiningPercentage() {
        return employeeJoiningPercentage;
    }

    public void setEmployeeJoiningPercentage(float employeeJoiningPercentage) {
        this.employeeJoiningPercentage = employeeJoiningPercentage;
    }

//    public char getEmployeePerformance() {
//        return employeePerformance;
//    }
//
//    public void setEmployeePerformance(char employeePerformance) {
//        this.employeePerformance = employeePerformance;
//    }

    public String getCOMPANY_NAME() {
        return COMPANY_NAME;
    }

    public void displayEmployeeDetails(){

        System.out.println("Welcome to the employee profile");
        System.out.println("The details of the employees are......");

        System.out.println("Name: " + employeeName);
        System.out.println("Age: "  + employeeAge);
        System.out.println("City: " + employeeCity);
        System.out.println("Company Name: " + getCOMPANY_NAME());
        System.out.println("Joining Percentage: " + employeeJoiningPercentage);
//        System.out.println("Performance Grade: " + employeePerformance);

    }

    public void performanceRating(){

        if(employeeJoiningPercentage > 90){
            employeePerformance = 'A';
        } else if ( employeeJoiningPercentage > 75.00) {
            employeePerformance = 'B';
        } else if ( employeeJoiningPercentage > 60){
            employeePerformance = 'C';
        } else if (employeeJoiningPercentage < 60){
            employeePerformance = 'D';
        }

        System.out.println("Performance Grade: " + employeePerformance);

        switch(employeePerformance){
            case 'A':
                System.out.println("Comment: Star Performer");
                break;
            case 'B':
                System.out.println("Comment: Strong Start");
                break;
            case 'C':
                System.out.println("Comment: Satisfactory");
                break;
            case 'D':
                System.out.println("Comment: Needs Mentorship");
                break;
            default:
                System.out.println("Comment: Default case check input");
        }
    }
}
