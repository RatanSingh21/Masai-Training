public class Animal {

    private String name;

    // Getter and Setter
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    // Non- Parameterized Constructor
    public Animal(){
        System.out.println("This is non parameterized constructor....");
    }

    // Parameterized Constructor
    public Animal(String name){
        System.out.println("This is parameterized constructor....");
        this.name = name;
    }

    // Methods
    public void eat(){
        System.out.println("The " + this.name + " is eating....");
    }

    public void sleep(){
        System.out.println("The " + this.name + " is sleeping...");
    }

    public void makenoise(){
        System.out.println("The " + this.name + " is making noise");
    }

    public void roams(){
        System.out.println("The " + this.name +" is roaming");
    }
}
