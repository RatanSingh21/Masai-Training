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
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private EmployeeRepository employeeRepository;

    @GetMapping("/employees/{id}")
    public ResponseEntity<Employee> getEmployeeByID(@PathVariable("id") Long id){
        return employeeService.getEmployeeById(id);
    }

    @GetMapping("/employee/{department}")
    public ResponseEntity<List<Employee>> getEmployeesByDepartment(@PathVariable("department")  String department){
        return ResponseEntity.ok(employeeService.getByDepartment(department));
    }

    @PostMapping("employee/add")
    public ResponseEntity<String> addEmployee (@RequestBody Employee employee){
        employeeRepository.save(employee);
        return ResponseEntity.ok("Employee ADDEED SUCCESSFULLY");
    }

}
