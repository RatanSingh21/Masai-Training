//package in.ratansgh.security.security;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.Customizer;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.crypto.password.NoOpPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.provisioning.InMemoryUserDetailsManager;
//import org.springframework.security.web.SecurityFilterChain;
//import org.springframework.security.config.Customizer;
//
//
//@Configuration
//@EnableWebSecurity
//public class SecurityConfig {
//
//    @Bean
//    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//        http
//                .csrf(csrf -> csrf.disable() )
//                .authorizeHttpRequests(authentication -> authentication
//                        .requestMatchers("/hello").permitAll()
//                        .requestMatchers("/hello/auth").authenticated()
//                )
//                .httpBasic(Customizer.withDefaults());
//
//        return http.build();
//    }
//
//    @Bean
//    public InMemoryUserDetailsManager userDetails(){
//        InMemoryUserDetailsManager inMemUser = new InMemoryUserDetailsManager();
//
//        UserDetails user = User.withUsername("user")
//                .password("12345") // we need to encode the password
////                .authorities("read") // has only read access
//                .roles("user")
//                .build();
//
//        inMemUser.createUser(user);
//
//        return inMemUser;
//    }
//
//    @Bean
//    public PasswordEncoder passwordEncode(){
//        return NoOpPasswordEncoder.getInstance();
//    }
//}
