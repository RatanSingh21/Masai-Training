package in.ratansgh;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;


public class Main {
    public static void main(String[] args) {


//        xml based approach for registering the bean
//        ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
//        A a1 = ctx.getBean("a1" ,A.class);
//        a1.func1();

//        Student s2 = ctx.getBean("s2", Student.class);

//      annotation based approach for registering the bean
        AnnotationConfigApplicationContext context =  new AnnotationConfigApplicationContext(Config.class);
        A a1 = context.getBean("a1", A.class);
        a1.func1();

    }
}