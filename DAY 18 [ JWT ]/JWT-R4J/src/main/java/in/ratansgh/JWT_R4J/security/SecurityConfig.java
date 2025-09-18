package in.ratansgh.JWT_R4J.security;

import in.ratansgh.JWT_R4J.filters.JwtValidatorFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain (HttpSecurity http) throws Exception {

        http
                .csrf(csrf -> csrf.disable() )
                .authorizeHttpRequests((authz) -> authz
                    .requestMatchers("/welcome/auth").authenticated()
                    .requestMatchers("/welcome", "/register").permitAll()
                    .requestMatchers("/admin").hasRole("ADMIN")
                        .requestMatchers("/user").hasRole("USER")
            )
//                .addFilterAfter(new JwtGeneratorFilter(), BasicAuthenticationFilter.class) // Add JwtGeneratorFilter after BasicAuthenticationFilter to generate JWT after successful authentication
                .addFilterBefore(new JwtValidatorFilter(), BasicAuthenticationFilter.class) // Add JwtValidatorFilter before BasicAuthenticationFilter to validate JWT before authentication
        .httpBasic(Customizer.withDefaults());
        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
