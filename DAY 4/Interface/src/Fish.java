public class Fish extends Animal {

    public Fish(String name) {
        super(name);
    }

    @Override
    public void eat() {

    }

    @Override
    public void makenoise() {

    }

    @Override
    public void roam() {

    }

    public void swim(){
        System.out.println(getName() + " is swimming in Fish class...");
    }
}