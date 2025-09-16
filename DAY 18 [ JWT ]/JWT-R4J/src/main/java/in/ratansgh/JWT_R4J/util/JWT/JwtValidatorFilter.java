package in.ratansgh.JWT_R4J.util.JWT;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.filter.OncePerRequestFilter;
import javax.crypto.SecretKey;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class JwtValidatorFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        // Get JWT token from request header
        String jwtToken = request.getHeader(JwtConstant.jwt_header);

        if (null != jwtToken){

            try {

                // Create secret key for JWT validation
                SecretKey key = Keys.hmacShaKeyFor(JwtConstant.jwt_key.getBytes(StandardCharsets.UTF_8)); // this is the same key that was used to sign the JWT

                // Parse and validate JWT token, extract claims --->>
                Claims claims = Jwts.parser()// parser instance is used to parse and validate JWT tokens
                        .setSigningKey(key) // Sets the secret key used to verify the token’s signature. This ensures the token was signed by your server
                        .build()// build the parser
                        .parseClaimsJws(jwtToken) // Parses the JWT string, verifies its signature, and returns a Jws<Claims> object if valid.
                        .getBody(); // Extracts the claims (payload) from the token, which contains user data like username and authorities.

                // Extract username and authorities from claims
                String username = String.valueOf(claims.get("username"));
                String rawAuthorities = String.valueOf(claims.get("authorities"));
                System.out.println( "Authorities in the jwt token are as follows: " + rawAuthorities);

                // Parse authorities string to create GrantedAuthority

                // Splits the authorities string using any of the characters {, [, =, ], or } as delimiters. This produces an array of substrings.
                // String[] at = rawAuthorities.split("[{[=]}]");

                List<?> rolesRaw = (List<?>) claims.get("authorities");
                List<GrantedAuthority> authorities = new ArrayList<>();
                for (Object roleObj : rolesRaw) {
                    String role;
                    if (roleObj instanceof String) {
                        role = (String) roleObj;
                    } else if (roleObj instanceof java.util.LinkedHashMap) {
                        role = (String) ((java.util.LinkedHashMap<?, ?>) roleObj).get("authority");
                    } else {
                        continue;
                    }
                    authorities.add(new SimpleGrantedAuthority(role));
                }

                System.out.println("Parsed roles from JWT token: " + rolesRaw);
                System.out.println("Granted Authorities: " + authorities);

                // Create Authentication object and set it in SecurityContext
                Authentication authentication = new UsernamePasswordAuthenticationToken(username, null, authorities);
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }

            catch (Exception e){

                e.printStackTrace(); // Add this for debugging

                // If token is invalid or expired, throw exception
                throw new UsernameNotFoundException("JWT Token is expired or invalid");
            }
        }

        // Continue filter chain
        filterChain.doFilter(request, response);
    }

    @Override
    public boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        // This filter should not be applied to the /register endpoint and /login endpoint technically
        return request.getServletPath().equals("/register");
    }
}
