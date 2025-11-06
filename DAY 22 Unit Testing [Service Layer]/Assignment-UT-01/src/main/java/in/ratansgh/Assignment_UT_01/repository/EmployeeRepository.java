package in.ratansgh.Assignment_UT_01.repository;

import in.ratansgh.Assignment_UT_01.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
}

