public class Main {
    public static void main(String[] args) {

        // Constructor for Animal class
        Animal an = new Animal("Tiger");
        an.makenoise();

        // Constructor for Cat class
        Cat ca = new Cat("Tiger");
        ca.makenoise(); //

        // Constructor i.e. Object type casting
        Animal ani = new Cat("Anime");
        // ani.jump(); // can't be done since jump is not in Cat class. We need to type cast it

//        // Type casted the ani object
//        Cat c =(Cat)ani;
//        c.jump();


        // Dog class constructor
        Dog d = new Dog("cutie");
        Cat c = new Cat("catty");

        //AnimalUtils class constructor
        AnimalUtils utils = new AnimalUtils();
        utils.addanimal(d);
        utils.addanimal(c);



    }
}
