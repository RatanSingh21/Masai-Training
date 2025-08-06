public class Animal {

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Animal(String name) {
        this.name = name;
    }

    public void eat(){
        System.out.println("The " + name + " is eating");
    }

    public void makenoise(){
        System.out.println("The " + name + " is making noise");
    }

    public void sleep(){
        System.out.println("The " + name + " is sleeping");
    }

    public void roam(){
        System.out.println("The " + name + "is roaming");
    }

}
