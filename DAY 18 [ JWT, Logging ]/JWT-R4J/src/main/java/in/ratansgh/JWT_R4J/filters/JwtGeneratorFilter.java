package in.ratansgh.JWT_R4J.filters;

import in.ratansgh.JWT_R4J.util.JWT.JwtConstant;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.security.core.GrantedAuthority;

import javax.crypto.SecretKey;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Calendar;
import java.util.Date;

// A filter that generates a JWT token for authenticated users and adds it to the response header.
public class JwtGeneratorFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        // Jwt token should be generated only when user is authenticated and after authentication, user data is stored in SecurityContextHolder
        SecurityContext context = SecurityContextHolder.getContext(); // Gets the current security context, which holds authentication and security details for the thread.
        Authentication auth = context.getAuthentication(); // Retrieves the Authentication object from the context,

        if (auth != null){

            Date iatDate = new Date(); // issued at
            Date expDate = new Date(); // expiration date

            // Gets a calendar instance to manipulate date and time.
            Calendar calendar = Calendar.getInstance();
            // Sets the calendar's time to the current date and time.
            calendar.setTime(expDate);
            // Adds 4 hours to the current time, setting the token's expiration to 4 hours from now.
            calendar.add(Calendar.HOUR, 4);

            // Generates a secret key for signing the JWT using the provided key string
            SecretKey key = Keys.hmacShaKeyFor(JwtConstant.jwt_key.getBytes(StandardCharsets.UTF_8));

            // generate JWT token and add it to the response header
            String jwtToken = Jwts.builder()
                    .setIssuer("Ratansgh")
                    .setSubject("JWT Token")
                    .claim("username", auth.getName())
//                    .claim("authorities", auth.getAuthorities()) // directly adding authorities list to the claim

                    // After:
                    .claim("authorities", auth.getAuthorities().stream()
                            .map(GrantedAuthority::getAuthority)
                            .toList())
                    .setIssuedAt(iatDate)
                    .setExpiration(calendar.getTime())
                    .signWith(SignatureAlgorithm.HS256,key)
                    .compact(); // Builds and serializes the JWT to a compact, URL-safe string.

            response.setHeader(JwtConstant.jwt_header, jwtToken); // Add the generated JWT token to the response header

        }

        filterChain.doFilter(request, response); // Continue the filter chain

        System.out.println("JwtGeneratorFilter executed with token generated: " + response.getHeader(JwtConstant.jwt_header));

    }

    @Override
    public boolean shouldNotFilter(HttpServletRequest request) {
        String path = request.getServletPath();
        // Only run filter for /login
        return !path.equals("/login");
    }


    //  never use generation filter in FilterChain after BasicAuthenticationFilter because BasicAuthenticationFilter is responsible for processing basic authentication credentials
    //  (like username and password)
}
