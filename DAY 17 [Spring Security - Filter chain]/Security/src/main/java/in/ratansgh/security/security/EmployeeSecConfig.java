package in.ratansgh.security.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.config.Customizer;


@Configuration
public class EmployeeSecConfig {

    @Bean
    public SecurityFilterChain employeeSecurityConfig(HttpSecurity http) throws Exception {

        http
                .csrf(csrf -> csrf.disable() )
                .authorizeHttpRequests(authentication -> authentication
                .requestMatchers("/welcome/auth").authenticated()
                .requestMatchers("/admin").hasRole("ADMIN") // only admin can access /admin ALSO role should be in uppercase in DB
                .requestMatchers("/welcome", "/register").permitAll()
        )
                .httpBasic(Customizer.withDefaults());

        return http.build();
    }

//    this stores password in plain text, not recommended for production
//    @Bean
//    public PasswordEncoder passwordEncoder(){
//        return NoOpPasswordEncoder.getInstance();
//    }

//    this stores password in encrypted format, recommended for production
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
