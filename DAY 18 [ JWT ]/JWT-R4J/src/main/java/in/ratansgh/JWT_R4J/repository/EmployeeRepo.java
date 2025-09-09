package in.ratansgh.JWT_R4J.repository;

import in.ratansgh.JWT_R4J.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepo extends JpaRepository<Employee, Integer> {

    Employee findByUsername(String username);
}
