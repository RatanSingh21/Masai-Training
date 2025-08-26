package in.ratansgh;

public class Travel {

    // Dependency.....
    Vehicle v;// dependency of object type
    int numberOfPerson; // dependency of primitive type

    // Required for setter injection
    public void setV(Vehicle v) {
        this.v = v;
    }
    public void setNumberOfPerson(int numberOfPerson) {
        this.numberOfPerson = numberOfPerson;
    }

    // Required for Constructor injection
    public Travel(Vehicle v, int numberOfPerson) {
        this.v = v;
        this.numberOfPerson = numberOfPerson;
    }

    public void journey(){
        v.go();
        System.out.println("Journey Started with " +numberOfPerson+ " people....");
    }

}
