package in.ratansgh.JWT_R4J.util.JWT;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class JwtGeneration {

    public static String generateToken(String username, List<SimpleGrantedAuthority> authorities){
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
        return Jwts.builder()
                .setIssuer("Ratansgh")
                .setSubject("JWT Token")
                .claim("username", username)
                .claim("authorities", authorities) // directly adding authorities list to the claim
                .setIssuedAt(iatDate)
                .setExpiration(calendar.getTime())
                .signWith(SignatureAlgorithm.HS256,key)
                .compact(); // Builds and serializes the JWT to a compact, URL-safe string.
    }
}
