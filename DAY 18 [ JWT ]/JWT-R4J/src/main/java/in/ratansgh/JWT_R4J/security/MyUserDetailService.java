package in.ratansgh.JWT_R4J.security;

import in.ratansgh.JWT_R4J.entity.Employee;
import in.ratansgh.JWT_R4J.repository.EmployeeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MyUserDetailService implements UserDetailsService {

    @Autowired
    private EmployeeRepo employeeRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Employee employee = employeeRepo.findByUsername(username);

        if (employee == null){
            throw new UsernameNotFoundException("User not found");
        }

        return new MyUserDetails(employee);
    }
}
