package in.ratansgh;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {
    public static void main(String[] args) {

        // registered the applicationcontext file
        ApplicationContext ctx = new ClassPathXmlApplicationContext("ApplicationContext.xml");
/*
    Here we never created the object of MyBusiness class like using constructor and new keyword ,it has been managed
    by the Spring Container....
*/

//        // calling the registered bean
//        // getBean("bean_id_name" , "class_name")
//        MyBusiness mb = ctx.getBean("MyBusiness", MyBusiness.class);
//
//        // Access the method of  Mybusiness class.
//        mb.func1();


/*
    Without Spring Container, setter injection
 */
//        // obj of class travel
//        Travel tr = new Travel();
//        //  object inside class travel needs to set either car or bike hence dependency
//        tr.setV(new Car());
//        tr.journey();


/*
    With Spring Container, we need different bean id for different class.
 */
//        Travel trC = ctx.getBean("Travel_Car", Travel.class);
//        trC.journey();
//        System.out.println("\n");
//
//        Travel trB = ctx.getBean("Travel_Bike", Travel.class);
//        trB.journey();

        Travel trCons = ctx.getBean("Travel_Contructor_Bike", Travel.class);
        trCons.journey();


    }
}
