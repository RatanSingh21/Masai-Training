package in.ratansgh.JWT_R4J.security;

import in.ratansgh.JWT_R4J.entity.Employee;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;


public class MyUserDetails implements UserDetails {

    private Employee employee;

    public MyUserDetails(Employee employee) {
        this.employee = employee;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<GrantedAuthority> authorities = new ArrayList<>();
        SimpleGrantedAuthority simpleGrantedAuthority = new SimpleGrantedAuthority("ROLE_" + employee.getRole());
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
