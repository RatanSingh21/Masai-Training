import javax.xml.transform.Source;
import java.util.*;

public class Main {
    public static void main(String[] args) {

        // Collection Framework -->> List, Queue, Set

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

//        Map -->> HashMap, LinkedList, TreeMap

//        Maps -- >> HashMap

        HashMap<Integer, Employee> HashMap = new HashMap<>();
        HashMap.put(1 , new Employee("Ratan" , "1" ,123456,"GET"));
        HashMap.put(2 , new Employee("Vig" , "2" ,3456,"GET"));

//        System.out.println(HashMap);

        // LinkedHashMap -->> Maintains the order of Insertion
        // TreeMap -->> Insert order.

        // Hashcode

        // Maps not part of JCF

        Map<Integer , Employee> EmployeeMap = new HashMap<>();
        EmployeeMap.put(1 , new Employee("Ratan" , "1" ,123456,"GET"));
        EmployeeMap.put(2 , new Employee("Vig" , "2" ,3456,"GET"));

//        System.out.println(EmployeeMap.get(1));

        // Printing specific fields
//        EmployeeMap.forEach((key,Value)-> System.out.println("ID of employee: " + key + " Name is: " + Value.getName()));

        // EntrySet
        Set<Map.Entry<Integer,Employee>> EmployeeEntrySet = EmployeeMap.entrySet();

//        for(Map.Entry<Integer, Employee> ENTRY : EmployeeEntrySet){
//            System.out.println("The entry set of ID is: " + ENTRY.getKey());
//            System.out.println("The entry set of Values is: " + ENTRY.getValue());
//            System.out.println("**********----------********************");
//        }

    }
}