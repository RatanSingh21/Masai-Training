public class Parrot extends Bird implements Flyable{

        public Parrot(String name) {
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

//  normal implementation
    @Override
    public void fly() {
        System.out.println( getName() + " fly method implemented in Parrot class...");
    }



}