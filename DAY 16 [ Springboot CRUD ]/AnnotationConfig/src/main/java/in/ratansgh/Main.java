package in.ratansgh;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;


public class Main {
    public static void main(String[] args) {

        ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
        A a1 = ctx.getBean("a1" ,A.class);
        a1.func1();

//        Student s2 = ctx.getBean("s2", Student.class);


    }
}