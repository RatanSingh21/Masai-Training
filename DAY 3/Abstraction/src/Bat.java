public class Bat extends Animal{

    public Bat(String name) {
        super(name);
    }

    @Override
    public void eat() {
        System.out.println("The " +getName() + " eat method implemented in Bat class...");
    }

    @Override
    public void makenoise() {
        System.out.println( getName() + " Make noise method implemented in Bat class....");
    }

    @Override
    public void roam() {
        System.out.println(getName() +" Roam method implemented in Bat class...");
    }
}
