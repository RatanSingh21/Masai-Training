import java.util.*;
import java.util.stream.Collectors;

public abstract class Main implements BonusCalculator{
    public static void main(String[] args) {

        List<Employee> employees = Arrays.asList(
                new Employee(1, "Alice", "IT", 60000),
                new Employee(2, "Bob", "Finance", 45000),
                new Employee(3, "Charlie", "IT", 70000),
                new Employee(4, "David", "HR", 40000),
                new Employee(5, "Eva", "Finance", 80000)
        );

        // 1. Filter: Employees with salary > 50,000
        List<Employee> highEarners = employees.stream()
                .filter(e -> e.getSalary() > 50000)
                .collect(Collectors.toList());
        System.out.println("---------- High Earners (Salary > 50000) ------------");
        highEarners.forEach(System.out::println);

        // 2. Map: List of employee names in uppercase
        List<String> upperCaseNames = employees.stream()
                .map(e -> e.getName().toUpperCase())
                .collect(Collectors.toList());
        System.out.println("\n ----------------- Employee Names in Uppercase --------------");
        upperCaseNames.forEach(System.out::println);


        // 3. Grouping: By department
        Map<String, List<Employee>> employeesByDept = employees.stream()
                .collect(Collectors.groupingBy(Employee::getDepartment));
        System.out.println("\n --------- Employees Grouped by Department ---------------------");
        employeesByDept.forEach((dept, empList) -> {
            System.out.println(dept + ": " + empList);
        });

        // 4. Sorting: By salary descending
        List<Employee> sortedBySalaryDesc = employees.stream()
                .sorted(Comparator.comparingDouble(Employee::getSalary).reversed())
                .collect(Collectors.toList());
        System.out.println("\n-------------- Employees Sorted by Salary (Descending) ----------------");
        sortedBySalaryDesc.forEach(System.out::println);

        // 5. Aggregation: Average salary
        double avgSalary = employees.stream()
                .mapToDouble(Employee::getSalary)
                .average()
                .orElse(0.0);
        System.out.println("\n ----------------- Average Salary -------------------- " );
        System.out.println("Average Salary is: " + avgSalary);


        // 6. Optional: First employee safely
        Optional<Employee> firstEmployee = employees.stream().findFirst();
        System.out.println("\n ------------------- First Employee (Safely with Optional) -----------------");
        firstEmployee.ifPresent(System.out::println);


        // 7. Custom Functional Interface: Bonus calculator
        BonusCalculator bonusCalculator = salary -> salary * 1.10; // 10% bonus

        System.out.println("\n -------------------- Employees with 10% Bonus Applied --------------------");
        employees.forEach(emp -> {
            double updatedSalary = bonusCalculator.calculate(emp.getSalary());
            emp.setSalary(updatedSalary);
            System.out.println(emp);
        });

    }
}