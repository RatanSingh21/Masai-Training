package in.ratansgh;

import javax.swing.plaf.PanelUI;

public class ConstructorAutowiring {

     private A a1;
     private A a2;
     private B b1;

    public ConstructorAutowiring(A a1, A a2, B b1) {
        System.out.println("Inside all arg constructor....");
        this.a1 = a1;
        this.a2 = a2;
        this.b1 = b1;
    }

    public void showDetails(){
        System.out.println("Inside show detail methods....");
        System.out.println("a1: " + a1);
        System.out.println("a2: " + a2);
        System.out.println("b1: " + b1);

    }
}
