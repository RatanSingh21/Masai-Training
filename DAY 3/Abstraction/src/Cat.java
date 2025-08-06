public class Cat extends Animal{

    public Cat(String name) {
        super(name);
    }

    @Override
    public void eat() {
        System.out.println( getName() + " eat method implemented in cat class...");
    }

    @Override
    public void makenoise() {
        System.out.println( getName() + " Make noise method implemented in cat class....");
    }

    @Override
    public void roam() {
        System.out.println( getName() + " Roam method implemented in cat class...");
    }
}
