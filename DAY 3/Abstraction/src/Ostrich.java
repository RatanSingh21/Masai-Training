public class Ostrich extends Bird{

    public Ostrich(String name) {
        super(name);
    }

    @Override
    public void fly() {
        System.out.println( getName() + " fly method implemented in Ostrich class...");
    }

    @Override
    public void wings() {
        System.out.println( getName() + " wings method implemented in Ostrich class...");
    }

    @Override
    public void eat() {
        System.out.println( getName() + " eat method implemented in Ostrich class...");
    }

    @Override
    public void makenoise() {
        System.out.println( getName() + " Make noise method implemented in Ostrich class....");
    }

    @Override
    public void roam() {
        System.out.println( getName() + " Roam method implemented in Ostrich class...");
    }



}
