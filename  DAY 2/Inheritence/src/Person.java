public class Person {

    private String name;
    private int age;

    // Getter and Setter
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    // Constructor
    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    // Obj of address
    Address obj = new Address("Mumbai","Maharashtra", 421306);

    // Displaying the info of address
    public void displayinfo(){
        System.out.println("The information of the person are as follows: ");
        System.out.println("Name: " + name);
        System.out.println("Age: " + age);
        System.out.println("Address :" + obj.toString());
    }
}
