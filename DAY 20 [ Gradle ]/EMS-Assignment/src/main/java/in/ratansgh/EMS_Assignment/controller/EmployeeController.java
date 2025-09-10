package in.ratansgh.EMS_Assignment.controller;

import in.ratansgh.EMS_Assignment.entity.Employee;
import in.ratansgh.EMS_Assignment.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    // POST /employees - Add a new employee
    @PostMapping("/employees")
    public ResponseEntity<Employee> addEmployee(@RequestBody Employee employee) {
        Employee created = employeeService.addEmployee(employee);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    // GET /employees - Get all employees
    @GetMapping("/employees")
    public ResponseEntity<List<Employee>> getAllEmployees() {
        List<Employee> employees = employeeService.getAllEmployees();
        return new ResponseEntity<>(employees, HttpStatus.OK);
    }

    // GET /employees/{id} - Get employee by ID
    @GetMapping("employees/{id}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable Long id) {
        return employeeService.getEmployeeById(id)
                .map(emp -> new ResponseEntity<>(emp, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // PUT /employees/{id} - Update an employee
    @PutMapping("employees/{id}")
    public ResponseEntity<Employee> updateEmployee(@PathVariable Long id, @RequestBody Employee employee) {
        return employeeService.updateEmployee(id, employee)
                .map(updated -> new ResponseEntity<>(updated, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // DELETE /employees/{id} - Delete an employee
    @DeleteMapping("employees/{id}")
    public ResponseEntity<Void> deleteEmployee(@PathVariable Long id) {
        boolean deleted = employeeService.deleteEmployee(id);
        return deleted ? new ResponseEntity<>(HttpStatus.NO_CONTENT)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    // GET /employees/department/{dept} - Get employees by department
    @GetMapping("employees/department/{dept}")
    public ResponseEntity<List<Employee>> getEmployeesByDepartment(@PathVariable String dept) {
        List<Employee> employees = employeeService.getEmployeesByDepartment(dept);
        return new ResponseEntity<>(employees, HttpStatus.OK);
    }

    // GET /employees/salary/{min} - Get employees with salary > min
    @GetMapping("employees/salary/{min}")
    public ResponseEntity<List<Employee>> getEmployeesWithMinSalary(@PathVariable Double min) {
        List<Employee> employees = employeeService.getEmployeesWithMinSalary(min);
        return new ResponseEntity<>(employees, HttpStatus.OK);
    }

    // GET /employees/search/{name} - Search employees by name
    @GetMapping("employees/search/{name}")
    public ResponseEntity<List<Employee>> searchEmployeesByName(@PathVariable String name) {
        List<Employee> employees = employeeService.searchEmployeesByName(name);
        return new ResponseEntity<>(employees, HttpStatus.OK);
    }

    // GET /employees/emails - Get list of employee emails
    @GetMapping("employees/emails")
    public ResponseEntity<List<String>> getAllEmployeeEmails() {
        List<String> emails = employeeService.getAllEmployeeEmails();
        return new ResponseEntity<>(emails, HttpStatus.OK);
    }

    // GET /employees/average-salary - Get average salary of employees
    @GetMapping("employees/average-salary")
    public ResponseEntity<Double> getAverageSalary() {
        double avgSalary = employeeService.getAverageSalary();
        return new ResponseEntity<>(avgSalary, HttpStatus.OK);
    }

}
