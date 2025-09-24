package in.ratansgh.JWT_R4J.controller;

import in.ratansgh.JWT_R4J.entity.Employee;
import in.ratansgh.JWT_R4J.repository.EmployeeRepo;
import in.ratansgh.JWT_R4J.util.JWT.JwtGeneration;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.security.authentication.AuthenticationManager;


import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@RestController
@Slf4j
public class EmployeeController {

    private static final Logger logger = LoggerFactory.getLogger(EmployeeController.class);

    @Autowired
    private EmployeeRepo employeeRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;


    // OpenToAll but jwt token will be generated for the registered employee
    @PostMapping("/register")
    public ResponseEntity<Map<String, Object>> registerEmployee(@RequestBody Employee employee, HttpServletResponse response) throws Exception {

        Map<String, Object> resp;

        try {

            // encrypt password first before saving the employee
            logger.debug("password for employee send for encryption" );
            employee.setPassword(passwordEncoder.encode(employee.getPassword()));

            // save in database needs to be implemented in service package
            logger.debug("Employee saved in database" );
            Employee savedEmployee = employeeRepo.save(employee);

            // now since we don't have user details in security context, jwt token won't be generated i.e auth will
            // be null in JwtGeneratorFilter.java file
//          String role = String.valueOf(savedEmployee.getRole());
//          List<SimpleGrantedAuthority> authorities = List.of(new SimpleGrantedAuthority("ROLE_" + role));
//          String jwtToken = JwtGeneration.generateToken(savedEmployee.getUsername(), authorities);

            // Wrapping metadata for response
            resp = new LinkedHashMap<>();
            resp.put("status", "200");
            resp.put("message", "Employee registered successfully");
            resp.put("data", savedEmployee);
//            resp.put("jwt-token", jwtToken); // returning jwt token in response body

            logger.debug("JWT token generated and set in response header" );
            return new ResponseEntity<>(resp, HttpStatus.OK);

        } catch (Exception e) {

            logger.error(" Error while registering" );
            resp = new LinkedHashMap<>();
            resp.put("error", "Registration failed: " + e.getMessage());
            return new ResponseEntity<>(resp, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Map<String, String> loginRequest, HttpServletResponse response) {

        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            loginRequest.get("username"),
                            loginRequest.get("password")
                    )
            );

            // Extract authorities as SimpleGrantedAuthority
            List<SimpleGrantedAuthority> authorities = authentication.getAuthorities().stream()
                    .map(a -> new SimpleGrantedAuthority(a.getAuthority()))
                    .toList();
            String jwtToken = JwtGeneration.generateToken(authentication.getName(), authorities);

            logger.debug("JWT token set in header" );
            response.setHeader("Authorization", jwtToken);

            Map<String, Object> resp = new LinkedHashMap<>();
            resp.put("status", "200");
            resp.put("message", "Login successful");

            logger.debug("login successfully.." );
            return new ResponseEntity<>(resp, HttpStatus.OK);
        } catch (AuthenticationException e) {

            logger.error(" Error while login" );
            Map<String, Object> resp = new LinkedHashMap<>();
            resp.put("error", "Invalid username or password");
            return new ResponseEntity<>(resp, HttpStatus.UNAUTHORIZED);
        }
    }

    // OpenToAll but needs JWT token in header to access this page
    @GetMapping("/welcome")
    public ResponseEntity<String> welcome (){
        return new ResponseEntity<String>("Inside welcome page", HttpStatus.OK);
    }

    // to Admin only but needs JWT token in header to access this page
    @GetMapping("/admin")
    public ResponseEntity<String> admin (){
        return new ResponseEntity<String>("Welcome to admin page", HttpStatus.ACCEPTED);
    }

    // to Admin only but needs JWT token in header to access this page
    @GetMapping("/user")
    public ResponseEntity<String> user (){
        return new ResponseEntity<String>("Welcome to user page", HttpStatus.ACCEPTED);
    }

    // Protected -->> use username and password (without encoding) to access this page
    // protected -->> needs JWT token in header to access this page
    @GetMapping("/welcome/auth")
    public ResponseEntity<String> welcome2 (){

        try {
//            if (true) throw new RuntimeException("Test exception"); for testing
            return new ResponseEntity<String>("Inside welcome page authenticated", HttpStatus.OK);
        } catch (Exception e) {
            logger.warn("CODE FATT GYAYA");
            throw new RuntimeException(e);
        }
    }

    @GetMapping ("/logging")
    public ResponseEntity<String> loggingExample (){
        logger.trace("This is a TRACE level message");
        logger.debug("This is a DEBUG level message");
        logger.info("This is an INFO level message");
        logger.warn("This is a WARN level message");
        logger.error("This is an ERROR level message");

        return new ResponseEntity<String>("Logging example executed, check the logs for details.", HttpStatus.OK);
    }
}
