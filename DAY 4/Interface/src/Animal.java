public abstract class Animal {

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Animal(String name) {
        this.name = name;
    }

    // Abstract class
    public abstract void eat();

    public abstract void makenoise();

    public abstract void roam();


}