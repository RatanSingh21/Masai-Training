package in.teamPlan.secure_auth_api.util;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Component
public class JwtUtil {

    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.expiration}")
    private long jwtExpiration;

    private SecretKey getSigningKey() {
        return Keys.hmacShaKeyFor(secret.getBytes());
    }

    /**
     * Generate JWT token for a given username
     * @param username - User's username
     * @return JWT token string
     */
    public String generateToken(String username) {
        Map<String, Object> claims = new HashMap<>();
        return createToken(claims, username);
    }

    /**
     * Generate JWT token with custom claims
     * @param claims - Additional claims to include
     * @param username - User's username
     * @return JWT token string
     */
    public String generateToken(Map<String, Object> claims, String username) {
        return createToken(claims, username);
    }

    /**
     * Create JWT token with claims and subject
     * @param claims - Token claims
     * @param subject - Token subject (username)
     * @return JWT token string
     */
    private String createToken(Map<String, Object> claims, String subject) {
        Date now = new Date();
        Date expirationDate = new Date(now.getTime() + jwtExpiration);

        return Jwts.builder()
                .setClaims(claims)
                .setSubject(subject)
                .setIssuedAt(now)
                .setExpiration(expirationDate)
                .signWith(getSigningKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    /**
     * Extract username from JWT token
     * @param token - JWT token
     * @return username
     */
    public String getUsernameFromToken(String token) {
        return getClaimFromToken(token, Claims::getSubject);
    }

    /**
     * Extract expiration date from JWT token
     * @param token - JWT token
     * @return expiration date
     */
    public Date getExpirationDateFromToken(String token) {
        return getClaimFromToken(token, Claims::getExpiration);
    }

    /**
     * Extract issued date from JWT token
     * @param token - JWT token
     * @return issued date
     */
    public Date getIssuedDateFromToken(String token) {
        return getClaimFromToken(token, Claims::getIssuedAt);
    }

    /**
     * Extract specific claim from JWT token
     * @param token - JWT token
     * @param claimsResolver - Function to resolve specific claim
     * @return claim value
     */
    public <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = getAllClaimsFromToken(token);
        return claimsResolver.apply(claims);
    }

    /**
     * Extract all claims from JWT token
     * @param token - JWT token
     * @return Claims object
     */
    private Claims getAllClaimsFromToken(String token) {
        try {
            return Jwts.parserBuilder()
                    .setSigningKey(getSigningKey())
                    .build()
                    .parseClaimsJws(token)
                    .getBody();
        } catch (ExpiredJwtException e) {
            throw new RuntimeException("JWT token is expired", e);
        } catch (UnsupportedJwtException e) {
            throw new RuntimeException("JWT token is unsupported", e);
        } catch (MalformedJwtException e) {
            throw new RuntimeException("JWT token is malformed", e);
        } catch (SecurityException e) {
            throw new RuntimeException("JWT signature validation failed", e);
        } catch (IllegalArgumentException e) {
            throw new RuntimeException("JWT token is null or empty", e);
        }
    }

    /**
     * Check if JWT token is expired
     * @param token - JWT token
     * @return true if expired, false otherwise
     */
    public Boolean isTokenExpired(String token) {
        try {
            final Date expiration = getExpirationDateFromToken(token);
            return expiration.before(new Date());
        } catch (Exception e) {
            return true; // Consider token expired if any exception occurs
        }
    }

    /**
     * Validate JWT token
     * @param token - JWT token to validate
     * @return true if valid, false otherwise
     */
    public Boolean validateToken(String token) {
        try {
            Jwts.parserBuilder()
                    .setSigningKey(getSigningKey())
                    .build()
                    .parseClaimsJws(token);
            return !isTokenExpired(token);
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Validate JWT token for specific username
     * @param token - JWT token
     * @param username - Username to validate against
     * @return true if token is valid for the username
     */
    public Boolean validateToken(String token, String username) {
        try {
            final String tokenUsername = getUsernameFromToken(token);
            return (tokenUsername.equals(username) && !isTokenExpired(token));
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Get remaining time until token expiration in milliseconds
     * @param token - JWT token
     * @return remaining time in milliseconds
     */
    public Long getRemainingTimeInMillis(String token) {
        try {
            Date expiration = getExpirationDateFromToken(token);
            return expiration.getTime() - new Date().getTime();
        } catch (Exception e) {
            return 0L;
        }
    }

    /**
     * Get remaining time until token expiration in seconds
     * @param token - JWT token
     * @return remaining time in seconds
     */
    public Long getRemainingTimeInSeconds(String token) {
        return getRemainingTimeInMillis(token) / 1000;
    }

    /**
     * Check if token can be refreshed (not expired by more than refresh window)
     * @param token - JWT token
     * @return true if token can be refreshed
     */
    public Boolean canTokenBeRefreshed(String token) {
        try {
            final Date expiration = getExpirationDateFromToken(token);
            Date refreshThreshold = new Date(System.currentTimeMillis() - (jwtExpiration / 4)); // 25% of expiration time
            return expiration.after(refreshThreshold);
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Refresh JWT token (generate new token with extended expiration)
     * @param token - Existing JWT token
     * @return new JWT token
     */
    public String refreshToken(String token) {
        try {
            final Claims claims = getAllClaimsFromToken(token);
            String username = claims.getSubject();

            // Create new token with same claims but new expiration
            Map<String, Object> newClaims = new HashMap<>();
            claims.forEach((key, value) -> {
                if (!"exp".equals(key) && !"iat".equals(key)) {
                    newClaims.put(key, value);
                }
            });

            return generateToken(newClaims, username);
        } catch (Exception e) {
            throw new RuntimeException("Cannot refresh token", e);
        }
    }

    /**
     * Extract custom claim from token
     * @param token - JWT token
     * @param claimName - Name of the claim
     * @return claim value
     */
    public Object getCustomClaimFromToken(String token, String claimName) {
        try {
            final Claims claims = getAllClaimsFromToken(token);
            return claims.get(claimName);
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * Generate token with additional user information
     * @param username - Username
     * @param email - User email
     * @param roles - User roles
     * @return JWT token with additional claims
     */
    public String generateTokenWithUserInfo(String username, String email, String[] roles) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("email", email);
        claims.put("roles", roles);
        claims.put("tokenType", "access");
        return createToken(claims, username);
    }

    /**
     * Get token type from claims
     * @param token - JWT token
     * @return token type
     */
    public String getTokenType(String token) {
        try {
            Object tokenType = getCustomClaimFromToken(token, "tokenType");
            return tokenType != null ? tokenType.toString() : "access";
        } catch (Exception e) {
            return "unknown";
        }
    }

    /**
     * Check if token is of specific type
     * @param token - JWT token
     * @param expectedType - Expected token type
     * @return true if token matches expected type
     */
    public Boolean isTokenOfType(String token, String expectedType) {
        try {
            String tokenType = getTokenType(token);
            return expectedType.equals(tokenType);
        } catch (Exception e) {
            return false;
        }
    }
}
