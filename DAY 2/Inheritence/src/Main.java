//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {

        // Calling constructor for person
        Person Human = new Person("Ratan Singh" , 22);
        Human.displayinfo();

        // Calling constructor for employee
        Employee emp_01 = new Employee(Human.getName(), Human.getAge(), 01,"GET",3456.877F);
        emp_01.displayinfo();
        emp_01.applyforleave();

        // Calling constructor for student
        Student std_01 = new Student(01,"A", Human.getName(), Human.getAge());
        std_01.displayinfo();
    }
}