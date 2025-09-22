package in.ratansgh.Assignment_LMS.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public InMemoryUserDetailsManager inMemoryUserDetails(PasswordEncoder encoder) {
        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
        manager.createUser(User.withUsername("admin").password(encoder.encode("admin123")).roles("ADMIN").build());
        manager.createUser(User.withUsername("librarian").password(encoder.encode("lib123")).roles("LIBRARIAN").build());
        manager.createUser(User.withUsername("student").password(encoder.encode("student123")).roles("STUDENT").build());
        manager.createUser(User.withUsername("guest").password(encoder.encode("guest123")).roles("GUEST").build());
        return manager;
    }

    // src/main/java/in/ratansgh/Assignment_LMS/security/SecurConfig.java
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/", "/about", "/login", "/books/public").permitAll()
                        .requestMatchers(HttpMethod.GET, "/books", "/books/{id}", "/books/{id}/reserve").hasRole("STUDENT")
                        .requestMatchers(HttpMethod.POST, "/books").hasRole("LIBRARIAN")
                        .requestMatchers(HttpMethod.PUT, "/books/{id}").hasRole("LIBRARIAN")
                        .requestMatchers(HttpMethod.GET, "/reservations").hasRole("LIBRARIAN")
                        .requestMatchers(HttpMethod.POST, "/reservations/{id}/approve").hasRole("LIBRARIAN")
                        .requestMatchers(HttpMethod.DELETE, "/books/{id}").hasRole("ADMIN")
                        .requestMatchers("/users", "/admin/reports").hasRole("ADMIN")
                        .anyRequest().authenticated()
                )
                .formLogin(form -> form
                        .loginPage("/login")
                        .loginProcessingUrl("/perform_login")
                        .defaultSuccessUrl("/")
                        .permitAll()
                )
                .httpBasic(Customizer.withDefaults())
                .logout(logout -> logout
                        .logoutSuccessUrl("/login?logout")
                        .permitAll()
                )
                .exceptionHandling(ex -> ex
                        .accessDeniedPage("/access-denied")
                );
        return http.build();
    }

}
