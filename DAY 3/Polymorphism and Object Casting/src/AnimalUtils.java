public class AnimalUtils {

    public void addanimal(Animal animal){
        System.out.println("The " + animal.getName() + " is added in animalUtils");
        animal.makenoise();

        // Checking if the dog and cat is instance of Animal and if yes then type cast it
        if(animal instanceof  Cat){
            Cat cat = (Cat) animal;
            cat.jump();
        } else if(animal instanceof Dog){
            Dog dog = (Dog) animal;
            dog.play();
        }

    }
}
