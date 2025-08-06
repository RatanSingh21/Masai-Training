public class Dog extends Animal {

    public Dog(String name) {
        super(name);
    }

    public void eat(){
        System.out.println("The " + getName() + " is eating");
    }

    public void roam(){
        System.out.println("The " + getName() + " is roaming");
    }

    public void play(){
        System.out.println("The " + getName() + " is playing");
    }

    public void makenoise(){
        System.out.println("The " + getName() + " is making noise");
    }
}
