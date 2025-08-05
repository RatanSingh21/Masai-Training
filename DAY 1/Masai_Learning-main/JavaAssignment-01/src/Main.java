//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {

        // Created an Constructor for the class
        EmployeeOnboardingSystem emp_01 = new EmployeeOnboardingSystem();

        // Declaring the variables
        emp_01.setEmployeeName("Ratan Singh");
        emp_01.setEmployeeAge(22);
        emp_01.setEmployeeCity("Mumbai");
        emp_01.setEmployeeJoiningPercentage(89.35F);

        // Call the display function
        emp_01.displayEmployeeDetails();

        // Performance Rating
        emp_01.performanceRating();

    }
}