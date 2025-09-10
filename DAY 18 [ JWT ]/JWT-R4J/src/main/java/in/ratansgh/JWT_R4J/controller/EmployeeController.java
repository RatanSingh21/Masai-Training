package in.ratansgh.JWT_R4J.controller;

import in.ratansgh.JWT_R4J.entity.Employee;
import in.ratansgh.JWT_R4J.repository.EmployeeRepo;
import in.ratansgh.JWT_R4J.util.JwtGeneration;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@RestController
public class EmployeeController {

    @Autowired
    private EmployeeRepo employeeRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    // OpenToAll but jwt token will be generated for the registered employee
    @PostMapping("/register")
    public ResponseEntity<Map<String, Object>> registerEmployee(@RequestBody Employee employee, HttpServletResponse response) throws Exception {

        Map<String, Object> resp;

        try {

            // encrypt password first before saving the employee
            employee.setPassword(passwordEncoder.encode(employee.getPassword()));

            // save in database
            Employee savedEmployee = employeeRepo.save(employee);

            // now since we don't have user details in security context, jwt token won't be generated i.e auth will
            // be null in JwtGeneratorFilter.java file
            String role = String.valueOf(savedEmployee.getRole());
            List<SimpleGrantedAuthority> authorities = List.of(new SimpleGrantedAuthority(role));
            String jwtToken = JwtGeneration.generateToken(savedEmployee.getUsername(), authorities);


            // Wrapping metadata for response
            resp = new LinkedHashMap<>();
            resp.put("status", "success");
            resp.put("message", "Employee registered successfully");
            resp.put("data", savedEmployee);
            resp.put("jwtToken", jwtToken);

            return new ResponseEntity<>(resp, HttpStatus.OK);

        } catch (Exception e) {

            resp = new LinkedHashMap<>();
            resp.put("error", "Registration failed: " + e.getMessage());
            return new ResponseEntity<>(resp, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    // to Admin only but needs JWT token in header to access this page
    @GetMapping("/admin")
    public ResponseEntity<String> admin (){
        return new ResponseEntity<String>("Welcome to admin page", HttpStatus.ACCEPTED);
    }

    // OpenToAll but needs JWT token in header to access this page
    @GetMapping("/welcome")
    public ResponseEntity<String> welcome (){
        return new ResponseEntity<String>("Inside welcome page", HttpStatus.OK);
    }

    // Protected -->> use username and password (without encoding) to access this page
    // protected -->> needs JWT token in header to access this page
    @GetMapping("/welcome/auth")
    public ResponseEntity<String> welcome2 (){
        return new ResponseEntity<String>("Inside welcome page authenticated", HttpStatus.OK);
    }

}
