import javax.swing.plaf.synth.SynthOptionPaneUI;
import java.util.*;

public class Main {
    public static void main(String[] args) {

        // Collection Framework

//        List -->> ArrayList

        ArrayList<Employee> e = new ArrayList<>();
        e.add(new Employee("Ratan" , "1" ,123456,"GET"));
        e.add(new Employee("Vig" , "2" ,3456,"GET"));
        e.add(new Employee("Vasmi" , "3" ,5678,"GET"));

//        System.out.println(e);
//
//        System.out.println(e.get(1));

//        List -- >> LinkedList

        LinkedList<Employee> emp = new LinkedList<>();

        emp.add (new Employee("Ratan","1" , 12233, "ICC"));
        emp.add(new Employee("Vig" , "2" ,3456,"GET"));
        emp.addFirst(new Employee("Vasmi" , "3" ,5678,"GET"));

//        System.out.println(emp); // addfirst gets added first

//        Sets -->> HashSet

        HashSet<Employee> hashemployee = new HashSet<>();
        hashemployee.add (new Employee("Ratan","1" , 12233, "ICC"));
        hashemployee.add (new Employee("Ratan","1" , 12233, "ICC"));

//        System.out.println(hashemployee); // duplicates gets ignored

        // LinkedHashSet -->> Maintains insertion order
        // TreeSet -->> In natural order / sorted order

        // Queues -->> PriorityQueue

        PriorityQueue<Employee> PriorityQueue = new PriorityQueue<>((a,b) -> Double.compare(b.getSalary(),a.getSalary()));
        PriorityQueue.add(new Employee("Ratan" , "1" ,123456,"GET"));
        PriorityQueue.add(new Employee("Vig" , "2" ,3456,"GET"));
        PriorityQueue.add(new Employee("Vasmi" , "3" ,5678,"GET"));

//        System.out.println(PriorityQueue); // Can't print in this way bcoz Priority queue compares and needs to know on what basis we need to compare object hence use/ implement Comparable
//        while (!PriorityQueue.isEmpty()){
//            System.out.println(PriorityQueue.poll());
//        }

        // ArrayQueue

        ArrayDeque<Employee> ArrayDeque = new ArrayDeque<>();
        ArrayDeque.add(new Employee("Ratan" , "1" ,123456,"GET"));
        ArrayDeque.addLast(new Employee("Vig" , "2" ,3456,"GET"));
        ArrayDeque.addFirst(new Employee("Vasmi" , "3" ,5678,"GET"));

//        System.out.println(ArrayDeque);

//        Maps -- >> HashMap

        HashMap<Integer, Employee> HashMap = new HashMap<>();
        HashMap.put(1 , new Employee("Ratan" , "1" ,123456,"GET"));
        HashMap.put(2 , new Employee("Vig" , "2" ,3456,"GET"));

        System.out.println(HashMap.get(1));


        // Linked

    }
}