public class Cat extends Animal{

    // no need to create an variable in extended class
    //private String name;

//    public String getName() {
//        return name;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }


    // extending the Animal name in cat ,concept in Inheritance
    public Cat(String name) {
        super(name);
    }

    public void makenoise(){
        System.out.println("The " + getName()+ " is making noise");
    }

    public void jump(){
        System.out.println("The " + getName()+ " is jumping");
    }
}
