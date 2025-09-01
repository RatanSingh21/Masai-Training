package in.ratansgh;

//use any annotation of Stereotype

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.List;

// can be any annotations -- at class level
@Component(value = "a1")
//@Scope("Prototype") // will create diff instance of object
public class A {

    @Autowired
    @Qualifier("s1")
    private Student s3;

    @Autowired
    private List<String> cities;

    @Value("2")
    int rank;

    public void func1(){
        System.out.println("Inside function- 1 ");
//        s1.funcB();
        System.out.println(cities);
        System.out.println(rank);
        s3.funcB();
    }



}
