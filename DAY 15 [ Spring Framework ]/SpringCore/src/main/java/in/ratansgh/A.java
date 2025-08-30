package in.ratansgh;

// dependent class
public class A {

    //    -------------------- concept of dependency ------------------

//    B b1 = new B();// dependency
//
//    public void funcA(){
//        System.out.println("Inside Function A....");
//        b1.funcb();
//    }

//    -------------------- circular dependency ------------------
//
//    private B b1;
//
//    // constructor using class B object as dependency -- >> can't resolve
//    public A(B b1) {
//        this.b1 = b1;
//    }
//
//    // setter injection -- >> solves this circular error but requires default constructor
//    // Either 1 setter from both the class has to be initialized....
//    public void setB1(B b1) {
//        this.b1 = b1;
//    }
//
//    public A(){
//
//    }
//
//    public void showA(){
//        System.out.println("Inside Function A...");
//        System.out.println(b1);
//    }

    //    -------------------- Constructor Mode Autowiring ------------------

    public void funca(){
        System.out.println("Inside function A...");
    }

    public A() {
    }
}
