import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Comparator;
import java.util.EnumMap;
import java.util.List;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {

        // Even Number from a list
        List<Integer> numbers = Arrays.asList(1,2,3,45,6);
        List<Integer> evenNumber = numbers.stream()
                .filter(n-> n%2 == 0)
                .collect(Collectors.toList());

        // System.out.println(evenNumber);

        // Find the names of Developers from Class Employees
        List<Employees> employees = Arrays.asList(
                new Employees("Ratan" ,"Developer"),
                new Employees("Vig" ,"Tester")
        );

        List<String> developers = employees.stream()
                .filter(e-> e.getRole().equals("Developer"))
                .map(Employees::getName)
                .collect(Collectors.toList());

        // System.out.println(developers);

        // Count words Starting with "A"
        List<Methods> words = Arrays.asList(
                new Methods("Ratan"),
                new Methods("Vignesh")
        );

        long count = words.stream()
                .filter(w -> w.getWords().startsWith("A"))
                .count();

        // System.out.println(count);

        // Remove Duplicates from the list i.e. object -->> use comparator and comparable interface
//        List<Methods> duplicate = Arrays.asList(
//                new Methods(1),
//                new Methods(2),
//                new Methods(3),
//                new Methods(2),
//                new Methods(4)
//        );

        List<Integer> duplicate = Arrays.asList(1,2,3,3,4,5,5,6);
        List<Integer> spy = duplicate.stream()
                .distinct()
                .collect(Collectors.toList());

        System.out.println(spy);

        // Comparing salary
        List<Employees> salary = Arrays.asList(
                new Employees(6789045),
                new Employees(3456789)
        );

        List<Employees> sorted = salary.stream()
                .sorted(Comparator.comparing(Employees::getSalary))
                .collect(Collectors.toList());

        // System.out.println(sorted);
    }
}