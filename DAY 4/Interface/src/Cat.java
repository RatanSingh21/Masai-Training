public class Cat extends Animal{

    // extending the Animal name in cat ,concept in Inheritance
    public Cat(String name) {
        super(name);
    }

    public void makenoise(){
        System.out.println("The " + getName()+ " is making noise");
    }

    @Override
    public void eat() {

    }

    @Override
    public void roam() {

    }
}