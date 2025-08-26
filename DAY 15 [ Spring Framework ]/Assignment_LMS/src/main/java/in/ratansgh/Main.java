package in.ratansgh;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {
    public static void main(String[] args) {

        ApplicationContext context = new ClassPathXmlApplicationContext("ApplicationContent.xml");

        Library library = (Library) context.getBean("lib");
        library.displayDetails();

    }
}