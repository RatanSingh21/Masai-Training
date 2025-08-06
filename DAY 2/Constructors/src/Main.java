public class Main {
    public static void main(String[] args) {

        // Calling the class animal --- >> Parameterized..
        Animal Carnivorous = new Animal("Tiger");

        // Calling methods
        Carnivorous.eat();
        Carnivorous.makenoise();
        Carnivorous.roams();
        Carnivorous.sleep();

        // Calling the class Animal --- >> Non-Parameterized..
        Animal Herbivarous = new Animal();
        Herbivarous.setName("Rabbit");

        // Calling methods
        Herbivarous.eat();
        Herbivarous.makenoise();
        Herbivarous.roams();
        Herbivarous.sleep();

    }
}
