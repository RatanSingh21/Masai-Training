package in.ratansgh.security.security;

import in.ratansgh.security.model.Employee;
import in.ratansgh.security.repository.EmployeeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private EmployeeRepo employeeRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Employee employee = employeeRepo.findByUsername(username);

        if (employee == null){
            throw new UsernameNotFoundException("User not found in database to load in spring security");
        }
        // called the constructor from MyUserDetails
        return new MyUserDetails(employee);
    }
}
