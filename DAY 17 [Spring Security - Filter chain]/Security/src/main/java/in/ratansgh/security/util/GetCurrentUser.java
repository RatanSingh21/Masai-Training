package in.ratansgh.security.util;

import in.ratansgh.security.model.Employee;
import in.ratansgh.security.repository.EmployeeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class GetCurrentUser {

    @Autowired
    private EmployeeRepo employeeRepo;

    public Employee getLoggedInUserDetails() {


        // hold the info of login user -->> getContext()
        SecurityContext sc = SecurityContextHolder.getContext();
        // hold the role of the username, role and credentials
        Authentication auth = sc.getAuthentication();

        return employeeRepo.findByUsername(auth.getName());
    }
}
