public class Main {
    public static void main(String[] args) {

        // Abstract class cannot have objects hence we need to call methods directly i.e. no dog and bird..

        // dog class obj
        Dog d = new Dog("DOGGY");
        d.eat();
        d.makenoise();
        d.roam();
        d.sleep();

        // Cat class obj
        Cat c = new Cat("Catty");
        c.makenoise();
        c.roam();
        c.sleep();
        c.eat();

        // Bat class obj
        Bat b = new Bat("BATTY");
        b.eat();
        b.roam();
        b.makenoise();
        b.sleep();

        //fish class obj
        Fish f = new Fish("fishy");
        f.eat();


        // Parrot class obj
        Parrot p = new Parrot("patty");
        p.fly();
        p.makenoise();
        p.sleep();

        // Ostrich class obj
        Ostrich o = new Ostrich("ossty");
        o.roam();

    }
}