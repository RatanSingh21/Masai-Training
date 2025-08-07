public class Main {
    public static void main(String[] args) {

        // Dog and cat class constructor
        Dog d = new Dog("cutie");
        Cat c = new Cat("catty");

        // Parrot and fish class constructor
        Parrot p = new Parrot("parra");
        Fish f = new Fish("fishy");

        //AnimalUtils class constructor
        AnimalUtils utils = new AnimalUtils();
//        utils.addanimal(d);
//        utils.addanimal(c);
//        utils.addanimal(p);
//        utils.addanimal(f);

        // VarArgs
//        VarArgs var = new VarArgs();
//        var.addsum(4,5,6,6,78);

        // Lambda Function
        Walking w1 = () -> System.out.println("test");
       // System.out.println(w1.walk());
        w1.walk();

    }
}