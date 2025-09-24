package in.ratansgh.security.security;

import in.ratansgh.security.model.Employee;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;

// This is used to get the details of the user which implements UserDetails
// It is similar to InMemoryUserDetailsManager but that is in-memory user but for database auth implement this

public class MyUserDetails implements UserDetails {

    private Employee employee;

    public MyUserDetails(Employee employee) {
        this.employee = employee;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        // this is done to make Spring security know what is the role of the user/ employee
        Collection<GrantedAuthority> authorities = new ArrayList<>();
        SimpleGrantedAuthority simpleGrantedAuthority = new SimpleGrantedAuthority("ROLE_" + employee.getRole());
        // set the role as ROLE_admin or ROLE_user depending on
        // the role

        authorities.add(simpleGrantedAuthority);
        return authorities;
    }

    @Override
    public String getPassword() {
        return employee.getPassword();
    }

    @Override
    public String getUsername() {
        return employee.getUsername();
    }
}
