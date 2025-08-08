import java.util.List;
import java.util.stream.Collectors;


// How of Stream API works..
// Source -> A collection (like list map )
// Intermediate Operation -> like filter(), map ()
// Terminal Operation -> like collect(), forEach()


public class Main {
    public static void main(String[] args) {
        List<String> names = List.of("Ravi", "Ramesh", "Anu","Ajay");

        // Stream API
        names.stream()
                .filter(n-> n.startsWith("R")) // operation starts
                .map(n-> n.toUpperCase())
                .sorted()
                .forEach(n-> System.out.println(n)); // Terminal operation


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