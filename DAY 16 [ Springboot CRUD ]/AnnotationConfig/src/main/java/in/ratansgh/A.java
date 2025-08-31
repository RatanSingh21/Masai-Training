package in.ratansgh;

//use any annotation of Stereotype

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

// can be any annotations -- at class level
@Component(value = "a1")
public class A {

    @Autowired
    private Student s1;

    private Student s2;


    public A() {
    }

    public void func1(){
        System.out.println("Inside function- 1 ");
//        s1.funcB();
        s2.funcB();
    }



}
