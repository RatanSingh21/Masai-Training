public abstract class Bird extends Animal{

    public Bird(String name) {
        super(name);
    }

    public void display(){
        System.out.println("This is display function iod Bird class");
    }

    public abstract void fly();

    public abstract void wings();
}
