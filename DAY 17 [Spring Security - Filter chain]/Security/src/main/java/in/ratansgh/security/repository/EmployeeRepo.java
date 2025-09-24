package in.ratansgh.security.repository;

import in.ratansgh.security.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepo extends JpaRepository<Employee, Integer> {

    // use to find the username in database by this function
    public Employee findByUsername(String username);

}
