package in.ratansgh;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component(value = "s1")
public class Student {

    public void funcB(){
        System.out.println("Inside student function B");
    }


}
