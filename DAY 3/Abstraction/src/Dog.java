public class Dog extends Animal{

    public Dog(String name) {
        super(name);
    }

    @Override
    public void eat() {
        System.out.println( getName() + " eat method implemented in Dog class...");
    }

    @Override
    public void makenoise() {
        System.out.println( getName() + " Make noise method implemented in Dog class....");
    }

    @Override
    public void roam() {
        System.out.println( getName() + " Roam method implemented in Dog class...");
    }
}
