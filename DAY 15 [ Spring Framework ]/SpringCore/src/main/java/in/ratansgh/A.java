package in.ratansgh;

// dependent class
public class A {

    B b1 = new B();// dependency

    public void funcA(){

        System.out.println("Inside Function A....");
        b1.funcb();
    }

}
