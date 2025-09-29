package in.ratansgh.Gradle_Build_Tool.service;

import in.ratansgh.Gradle_Build_Tool.entities.Employee;
import in.ratansgh.Gradle_Build_Tool.respository.EmployeeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepo employeeRepo;

    public Employee employee (Employee employee) {
        return employeeRepo.save(employee);
    }

    public List<Employee> getAllEmployees() {
        return employeeRepo.findAll();
    }

    public List<Employee> getEmployeesByDepartment(String department) {
        return employeeRepo.findByDepartment(department);
    }

    public List<Employee> getEmployeesBySalary(Double salary) {
        return employeeRepo.findBySalary(salary);
    }
}
