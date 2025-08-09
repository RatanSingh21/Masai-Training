import java.util.*;

public class Main {
    public static void main(String[] args) {

//        List <String> ename = new ArrayList<>();
//        ename.add("Ratan");
//        ename.add("Dipesh");
//        ename.add("Soooman");
//        ename.add("QWERTYU");
//
//        System.out.println(ename);
//
//        System.out.println(ename.get(3));
//
//        for(Object e: ename){
////            String n = (String)e;
//            System.out.println(e);
//        }

        List <Employee> emp = new ArrayList<>();
        emp.add(new Employee("Ratan" , "01", 12345,"ICC"));
        emp.add(new Employee("Vigesh" , "02", 456789,"ICC"));
        emp.add(new Employee("dipes" , "03", 45678,"ICC"));
        emp.add(new Employee("momo" , "04", 34567,"ICC"));

        System.out.println("Before Sorting: ");
        System.out.println(emp);
        Collections.sort(emp);

        System.out.println("After Sorting: ");
        System.out.println(emp);

        Collections.sort(emp,(e1,e2)-> e1.getName().compareTo((e2.getName())));
        System.out.println(emp);

    }
}