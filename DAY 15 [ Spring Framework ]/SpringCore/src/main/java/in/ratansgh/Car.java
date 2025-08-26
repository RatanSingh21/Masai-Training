package in.ratansgh;

public class Car implements Vehicle{

    public void start(){
        System.out.println("Car Ignition started....");
    }


    @Override
    public void go() {
        start();
    }
}
