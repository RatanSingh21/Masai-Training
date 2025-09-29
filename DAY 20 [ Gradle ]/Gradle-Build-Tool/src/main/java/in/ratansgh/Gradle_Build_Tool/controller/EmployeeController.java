package in.ratansgh.Gradle_Build_Tool.controller;

import in.ratansgh.Gradle_Build_Tool.entities.Employee;
import in.ratansgh.Gradle_Build_Tool.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedHashMap;
import java.util.Map;

@RestController
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @GetMapping("/welcome")
    public String home() {
        return "Welcome to Gradle Build Tool Application";
    }

    @PostMapping("/register")
    public ResponseEntity<Map<String,Object>> register(@RequestBody Employee employee) {
        Map<String, Object > response = new LinkedHashMap<>();

        Employee registeredEmployee = employeeService.employee(employee);

        response.put("message", "User registered successfully");
        response.put("Employee", registeredEmployee);
        return new ResponseEntity<>(response, HttpStatus.CREATED);

    }

    @GetMapping("/employees")
    public ResponseEntity<Map<String, Object>> getAllEmployees() {

        Map<String, Object> response = new LinkedHashMap<>();

        response.put("message", "All employees retrieved successfully");
        response.put("Employees", employeeService.getAllEmployees());
        return new ResponseEntity<>(response, HttpStatus.OK);

    }

    @GetMapping("/employee/{department}")
    public ResponseEntity<Map<String, Object>> getEmployeesByDepartment(@PathVariable String department) {

        Map<String, Object> response = new LinkedHashMap<>();

        response.put("message", "Employees from department " + department + " retrieved successfully");
        response.put("Employees", employeeService.getEmployeesByDepartment(department));
        return new ResponseEntity<>(response, HttpStatus.OK);

    }

    @GetMapping("/employees/{salary}")
    public ResponseEntity<Map<String, Object>> getEmployeesBySalary(@PathVariable Double salary) {
        Map<String, Object> response = new LinkedHashMap<>();

        response.put("message", "Employees with salary " + salary + " retrieved successfully");
        response.put("Employees", employeeService.getEmployeesBySalary(salary));
        return new ResponseEntity<>(response, HttpStatus.OK);
    }


}
