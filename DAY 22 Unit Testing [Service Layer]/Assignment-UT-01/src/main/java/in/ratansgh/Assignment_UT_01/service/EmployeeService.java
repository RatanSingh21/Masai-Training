package in.ratansgh.Assignment_UT_01.service;

// EmployeeService.java

import in.ratansgh.Assignment_UT_01.entity.Employee;
import java.util.List;

public interface EmployeeService {
    Employee createEmployee(Employee employee);
    Employee getEmployeeById(Long id);
    List<Employee> getAllEmployees();
    Employee updateEmployee(Long id, Employee updatedEmployee);
    void deleteEmployee(Long id);
}
