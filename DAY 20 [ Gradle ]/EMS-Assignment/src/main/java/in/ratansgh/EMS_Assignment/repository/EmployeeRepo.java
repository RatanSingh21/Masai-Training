package in.ratansgh.EMS_Assignment.repository;

import in.ratansgh.EMS_Assignment.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepo extends JpaRepository<Employee, Long> {

    // 1. Find employees by department
    @Query("SELECT e FROM Employee e WHERE e.department = :department")
    List<Employee> findByDepartment(String department);

    // 2. Find employees with salary greater than a given amount
    @Query("SELECT e FROM Employee e WHERE e.salary > :minSalary")
    List<Employee> findBySalaryGreaterThan(double minSalary);

    // 3. Search employees by partial name match (first or last name)
    @Query("SELECT e FROM Employee e WHERE LOWER(e.firstName) LIKE LOWER(CONCAT('%', :name, '%')) " +
            "OR LOWER(e.lastName) LIKE LOWER(CONCAT('%', :name, '%'))")
    List<Employee> searchByName(String name);

    // 4. Get average salary of all employees
    @Query("SELECT AVG(e.salary) FROM Employee e")
    Double getAverageSalary();

    // 5. Get all employee emails
    @Query("SELECT e.email FROM Employee e")
    List<String> findAllEmails();

}
