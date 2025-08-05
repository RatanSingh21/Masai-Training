public class Student extends Person{
    private int rollno;
    private String grade;

    // Getter and Setters
    public int getRollno() {
        return rollno;
    }

    public void setRollno(int rollno) {
        this.rollno = rollno;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }


    // Constructor
    public  Student(int rollno, String grade, String name, int age) {
        super(name, age);
        this.rollno = rollno;
        this.grade = grade;
    }

    // Overriding the displayinfo
    public void displayinfo() {
        System.out.println("Student info is getting displayed as person is overridden...");
        System.out.println("Roll no: " + rollno);
        System.out.println("Grade: " + grade);

    }
}
