import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

public class Main {
    public static void main(String[] args) {

//        MathOperation var = new MathOperation();
//        var.addOperation(2,3);
//        var.subOperation(2,3);
//        var.mulOperation(2,3);
//        var.divOperation(2,3);

        // Lambda Parameter
//        MathOps add = (a,b)-> a+b;
//        System.out.println("The sum of two number is: " + add.addOperation(2,3));

        // Lambda Function
//        NamePrinter printyyy = () -> System.out.println("This is namyy from printtyy...");
//        printyyy.namyyy();

        // WITHOUT ANY INTERFACE USE CUSTOM in Functional Interface -->> In built features

        // Consumer -->> to take input
//        Consumer<String> printer = (s) -> System.out.println(s);
//        printer.accept("This is consumer in built generic class..");

        // Predicate -->> when some decision needs to be taken always returns true or false
//        Predicate<Integer> isEven = (n) -> n%2 == 0;
//        System.out.println( isEven.test(4));
//        System.out.println( isEven.test(7));

        // Function -->>
//        Function<Integer, String> concatInt = (n)-> "Number - " +n;
//        System.out.println(concatInt.apply(120));

        // Supplier -->>
        Supplier<Double> getRandomvaluegnerator =  ()-> Math.random();
        System.out.println(getRandomvaluegnerator.get());


    }
}