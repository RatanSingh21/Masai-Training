package in.ratansgh.Assignment_UT_02.controller;


import in.ratansgh.Assignment_UT_02.entities.Employee;
import in.ratansgh.Assignment_UT_02.repository.EmployeeRepository;
import in.ratansgh.Assignment_UT_02.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @GetMapping("/{id}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable Long id) {
        try {
            Employee employee = employeeService.getEmployeeById(id);
            return ResponseEntity.ok(employee);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/department/{dept}")
    public ResponseEntity<List<Employee>> getEmployeesByDepartment(@PathVariable("dept") String department) {
        List<Employee> employees = employeeService.getEmployeesByDepartment(department);
        return ResponseEntity.ok(employees);
    }
}
