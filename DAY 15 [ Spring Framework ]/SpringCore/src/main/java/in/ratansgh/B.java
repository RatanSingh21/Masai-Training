package in.ratansgh;

public class B {

    //    -------------------- concept of dependency ------------------

//    public void funcb(){
//        System.out.println("Inside Function B....");
//    }


//    //    -------------------- circular dependency ------------------
//
//    private A a1;
//
//    // constructor using class A dependency
//    public B(A a1) {
//        this.a1 = a1;
//    }
//
//    // no setter required but still we create
//    public void setA1(A a1) {
//        this.a1 = a1;
//    }
//
//    // default constructor
//    public B(){
//
//    }
//
//    public void showB(){
//        System.out.println("Inside Function B...");
//        System.out.println(a1);
//    }

    //    -------------------- Constructor Mode Autowiring ------------------

    public void funcb(){
        System.out.println("Inside function b...");
    }

    public B() {
    }
}
