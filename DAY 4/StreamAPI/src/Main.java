import java.sql.SQLOutput;
import java.util.List;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        List<String> names = List.of("Ravi", "Ramesh", "Anu","Ajay");

        // Stream API
        names.stream()
                .filter(n-> n.startsWith("R"))
                .map(n-> n.toUpperCase())
                .sorted()
                .forEach(n-> System.out.println(n));


        // Storing in new variable
        List<String> newNames = names.stream()
                .filter(n-> n.startsWith("R"))
                .map(String::toUpperCase)
                .sorted()
                .collect(Collectors.toList());

        System.out.println(newNames);

        // Count of vairbles in list
        long numNames = names.stream()
                .filter(n-> n.startsWith("R"))
                .map(String::toUpperCase)
                .sorted()
                .count();

        System.out.println("Total names starting with R are: " + numNames);
    }
}