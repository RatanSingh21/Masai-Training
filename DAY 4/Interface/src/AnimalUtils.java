public class AnimalUtils {


    public void addanimal(Animal animal){

        System.out.println("The " + animal.getName() + " is added in animalUtils");

        // Checking if the dog and cat is instance of Animal and if yes then type cast it
        if(animal instanceof  Cat){
            Cat cat = (Cat) animal;
            cat.makenoise();
        } else if(animal instanceof Dog){
            Dog dog = (Dog) animal;
            dog.makenoise();
        } else if (animal instanceof Parrot){
            Parrot parrot = (Parrot) animal;
            parrot.fly();
        } else if (animal instanceof Fish ){
            Fish fish = (Fish) animal;
            fish.swim();
        }

    }
}