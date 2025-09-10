package in.ratansgh.EMS_Assignment.service;

import in.ratansgh.EMS_Assignment.entity.Employee;

import java.util.List;
import java.util.Optional;

public interface EmployeeService {

    // CRUD methods
    Employee addEmployee(Employee employee);
    List<Employee> getAllEmployees();
    Optional<Employee> getEmployeeById(Long id);
    Optional<Employee> updateEmployee(Long id, Employee employee);
    boolean deleteEmployee(Long id);

    // Custom queries
    List<Employee> getEmployeesByDepartment(String department);
    List<Employee> getEmployeesWithMinSalary(double minSalary);
    List<Employee> searchEmployeesByName(String name);
    List<String> getAllEmployeeEmails();
    double getAverageSalary();

}
