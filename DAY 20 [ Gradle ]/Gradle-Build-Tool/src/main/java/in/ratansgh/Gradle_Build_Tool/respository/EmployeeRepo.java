package in.ratansgh.Gradle_Build_Tool.respository;

import in.ratansgh.Gradle_Build_Tool.entities.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepo extends JpaRepository<Employee,Long> {

    // Custom query method to find employees by department
    List<Employee> findByDepartment(String department);

    // Other query can be defined here as needed
    // Custom query to find employees by salary
    @Query("SELECT e FROM Employee e WHERE e.salary = :salary")
    List<Employee> findBySalary(@Param("salary") Double salary);

}
