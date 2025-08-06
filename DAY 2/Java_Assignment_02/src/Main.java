import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        // User Input
        Scanner sc = new Scanner(System.in);
//        System.out.println("Enter the name of the Employee: ");
//        String name = sc.nextLine();
//
//        System.out.println("Enter the ID of the employee: ");
//        String id = sc.nextLine();

//        System.out.println("Enter the Department of the Employee: ");
//        String department = sc.next();
//
//        // Calling constructor
//        Employee emp_01 = new Employee(id, name, department);
//        emp_01.showDetails();
//
//        // static concept
//        Employee.calculateBonus(100);
//        Employee.calculateBonus(100,5);
//
//        // Validation
//        Employee.validateID(id);
//
//        // Calling Training Constructor
//        TrainingModule subj = new TrainingModule("Java" , 71);
//        subj.display();

        // User for StringRverse
        System.out.println("Enter the String to be Reverse: ");
        String reverse = sc.next();

        StringBuilder reversedSentenceBuilder = new StringBuilder(reverse);
        reversedSentenceBuilder.reverse();
        String reversedSentence = reversedSentenceBuilder.toString();

        System.out.println("Original sentence: " + reverse);
        System.out.println("Reversed sentence: " + reversedSentence);

    }
}