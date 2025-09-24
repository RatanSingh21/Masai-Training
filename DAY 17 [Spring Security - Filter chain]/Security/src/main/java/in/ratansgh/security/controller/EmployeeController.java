package in.ratansgh.security.controller;

import in.ratansgh.security.model.Employee;
import in.ratansgh.security.repository.EmployeeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.LinkedHashMap;
import java.util.Map;

@RestController
public class EmployeeController {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private EmployeeRepo employeeRepo;

    // open to all
    @PostMapping("/register")
    public ResponseEntity<Map<String, Object>> registerEmployee(@RequestBody Employee employee) throws Exception {

        Map<String, Object> response;
        try {

            // encrypt password first before saving the employee
            employee.setPassword(passwordEncoder.encode(employee.getPassword()));
            System.out.println(employee.getPassword());

            // should be in service layer ideally
            Employee savedEmployee = employeeRepo.save(employee);

            // Wrapping metadata for response
            response = new LinkedHashMap<>();
            response.put("hashcode", savedEmployee.hashCode());
            response.put("data", savedEmployee);

            return new ResponseEntity<>(response, HttpStatus.OK);

        } catch (Exception e) {
            response = new LinkedHashMap<>();
            response.put("error", "Registration failed: " + e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    // to Admin only
    @GetMapping("/admin")
    public ResponseEntity<String> admin (){
        return new ResponseEntity<String>("Welcome to admin page", HttpStatus.ACCEPTED);
    }

    // OpenToAll
    @GetMapping("/welcome")
    public ResponseEntity<String> welcome (){
        return new ResponseEntity<String>("Inside welcome page", HttpStatus.OK);
    }

    // Protected -->> use username and password (without encoding) to access this page
    @GetMapping("/welcome/auth")
    public ResponseEntity<String> welcome2 (){
        return new ResponseEntity<String>("Inside welcome page authenticated", HttpStatus.OK);
    }

}
