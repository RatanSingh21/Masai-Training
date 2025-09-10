package in.ratansgh.JWT_R4J.util;

import in.ratansgh.JWT_R4J.entity.Employee;
import in.ratansgh.JWT_R4J.repository.EmployeeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

public class GetCurrentUser {

    @Autowired
    private EmployeeRepo employeeRepo;

    public Employee getLoggedInUserDetails(){

        SecurityContext sc = SecurityContextHolder.getContext();
        Authentication auth = sc.getAuthentication();

        return employeeRepo.findByUsername(auth.getName());

    }
}
