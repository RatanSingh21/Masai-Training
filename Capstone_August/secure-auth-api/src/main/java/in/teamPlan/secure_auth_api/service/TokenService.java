package in.teamPlan.secure_auth_api.service;

import in.teamPlan.secure_auth_api.util.JwtUtil;
import in.teamPlan.secure_auth_api.util.errorhandler.CustomExceptions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@Service
public class TokenService {

    private static final Logger logger = LoggerFactory.getLogger(TokenService.class);

    @Autowired
    private JwtUtil jwtUtil;

    // Thread-safe storage for active tokens
    private final Set<String> validTokens = ConcurrentHashMap.newKeySet();

    // Storage for token metadata (token -> timestamp)
    private final ConcurrentHashMap<String, LocalDateTime> tokenTimestamps = new ConcurrentHashMap<>();

    // Storage for user sessions (username -> active tokens)
    private final ConcurrentHashMap<String, Set<String>> userSessions = new ConcurrentHashMap<>();

    // Scheduled executor for cleanup tasks
    private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

    // Constructor to start cleanup scheduler
    public TokenService() {
        startTokenCleanupScheduler();
    }

    /**
     * Add a valid token to the active tokens set
     * @param token - JWT token to add
     */
    public void addToken(String token) {
        try {
            if (token == null || token.trim().isEmpty()) {
                throw new CustomExceptions.InvalidTokenException("Token cannot be null or empty");
            }

            // Validate token format and expiration
            if (!jwtUtil.validateToken(token)) {
                throw new CustomExceptions.InvalidTokenException("Invalid or expired token");
            }

            String username = jwtUtil.getUsernameFromToken(token);

            // Add token to valid set
            validTokens.add(token);
            tokenTimestamps.put(token, LocalDateTime.now());

            // Update user sessions
            userSessions.computeIfAbsent(username, k -> ConcurrentHashMap.newKeySet()).add(token);

            logger.info("Token added successfully for user: {}", username);

        } catch (Exception e) {
            logger.error("Error adding token: {}", e.getMessage());
            throw new CustomExceptions.InvalidTokenException("Failed to add token: " + e.getMessage());
        }
    }

    /**
     * Check if a token is valid (exists in active tokens and not expired)
     * @param token - JWT token to validate
     * @return true if token is valid, false otherwise
     */
    public boolean isTokenValid(String token) {
        try {
            if (token == null || token.trim().isEmpty()) {
                return false;
            }

            // Check if token exists in our valid set
            if (!validTokens.contains(token)) {
                return false;
            }

            // Double-check with JWT utility for expiration
            if (!jwtUtil.validateToken(token)) {
                removeToken(token); // Clean up expired token
                return false;
            }

            return true;

        } catch (Exception e) {
            logger.error("Error validating token: {}", e.getMessage());
            return false;
        }
    }

    /**
     * Remove a token from active tokens (logout)
     * @param token - JWT token to remove
     */
    public void removeToken(String token) {
        try {
            if (token == null || token.trim().isEmpty()) {
                return;
            }

            String username = null;

            // Try to get username before removing
            try {
                username = jwtUtil.getUsernameFromToken(token);
            } catch (Exception e) {
                logger.warn("Could not extract username from token during removal: {}", e.getMessage());
            }

            // Remove from valid tokens
            validTokens.remove(token);
            tokenTimestamps.remove(token);

            // Remove from user sessions
            if (username != null) {
                Set<String> userTokens = userSessions.get(username);
                if (userTokens != null) {
                    userTokens.remove(token);
                    if (userTokens.isEmpty()) {
                        userSessions.remove(username);
                    }
                }
            }

            logger.info("Token removed successfully for user: {}", username);

        } catch (Exception e) {
            logger.error("Error removing token: {}", e.getMessage());
        }
    }

    /**
     * Remove all tokens for a specific user (logout from all devices)
     * @param username - Username to logout from all sessions
     */
    public void removeAllTokensForUser(String username) {
        try {
            Set<String> userTokens = userSessions.get(username);
            if (userTokens != null) {
                // Remove all user tokens from valid set
                for (String token : userTokens) {
                    validTokens.remove(token);
                    tokenTimestamps.remove(token);
                }

                // Clear user sessions
                userSessions.remove(username);

                logger.info("All tokens removed for user: {}, count: {}", username, userTokens.size());
            }
        } catch (Exception e) {
            logger.error("Error removing all tokens for user {}: {}", username, e.getMessage());
        }
    }

    /**
     * Get count of active tokens
     * @return number of active tokens
     */
    public int getActiveTokenCount() {
        return validTokens.size();
    }

    /**
     * Get count of active tokens for a specific user
     * @param username - Username to check
     * @return number of active tokens for the user
     */
    public int getActiveTokenCountForUser(String username) {
        Set<String> userTokens = userSessions.get(username);
        return userTokens != null ? userTokens.size() : 0;
    }

    /**
     * Get all active usernames
     * @return set of usernames with active sessions
     */
    public Set<String> getActiveUsers() {
        return userSessions.keySet();
    }

    /**
     * Check if a user has any active sessions
     * @param username - Username to check
     * @return true if user has active sessions
     */
    public boolean hasActiveSession(String username) {
        return userSessions.containsKey(username) && !userSessions.get(username).isEmpty();
    }

    /**
     * Get token creation timestamp
     * @param token - JWT token
     * @return timestamp when token was added to service
     */
    public LocalDateTime getTokenTimestamp(String token) {
        return tokenTimestamps.get(token);
    }

    /**
     * Refresh a token (remove old, add new)
     * @param oldToken - Current token
     * @return new refreshed token
     */
    public String refreshToken(String oldToken) {
        try {
            if (!isTokenValid(oldToken)) {
                throw new CustomExceptions.InvalidTokenException("Cannot refresh invalid token");
            }

            String username = jwtUtil.getUsernameFromToken(oldToken);

            // Generate new token
            String newToken = jwtUtil.refreshToken(oldToken);

            // Remove old token and add new one
            removeToken(oldToken);
            addToken(newToken);

            logger.info("Token refreshed successfully for user: {}", username);
            return newToken;

        } catch (Exception e) {
            logger.error("Error refreshing token: {}", e.getMessage());
            throw new CustomExceptions.InvalidTokenException("Failed to refresh token: " + e.getMessage());
        }
    }

    /**
     * Clean up expired tokens
     */
    public void cleanupExpiredTokens() {
        try {
            int initialCount = validTokens.size();

            // Create a copy to avoid concurrent modification
            Set<String> tokensToCheck = Set.copyOf(validTokens);

            for (String token : tokensToCheck) {
                if (!jwtUtil.validateToken(token)) {
                    removeToken(token);
                }
            }

            int cleanedCount = initialCount - validTokens.size();
            if (cleanedCount > 0) {
                logger.info("Cleaned up {} expired tokens", cleanedCount);
            }

        } catch (Exception e) {
            logger.error("Error during token cleanup: {}", e.getMessage());
        }
    }

    /**
     * Start scheduled cleanup of expired tokens
     */
    private void startTokenCleanupScheduler() {
        // Run cleanup every 30 minutes
        scheduler.scheduleAtFixedRate(
                this::cleanupExpiredTokens,
                30, // Initial delay
                30, // Period
                TimeUnit.MINUTES
        );

        logger.info("Token cleanup scheduler started - running every 30 minutes");
    }

    /**
     * Get service statistics
     * @return formatted statistics string
     */
    public String getServiceStatistics() {
        return String.format(
                "TokenService Statistics - Active Tokens: %d, Active Users: %d, Total Sessions: %d",
                getActiveTokenCount(),
                getActiveUsers().size(),
                userSessions.values().stream().mapToInt(Set::size).sum()
        );
    }

    /**
     * Validate and get user from token
     * @param token - JWT token
     * @return username if token is valid
     * @throws CustomExceptions.InvalidTokenException if token is invalid
     */
    public String validateAndGetUser(String token) {
        if (!isTokenValid(token)) {
            throw new CustomExceptions.InvalidTokenException("Token is invalid or expired");
        }

        try {
            return jwtUtil.getUsernameFromToken(token);
        } catch (Exception e) {
            throw new CustomExceptions.InvalidTokenException("Could not extract user from token");
        }
    }

    /**
     * Shutdown cleanup scheduler (called when service is destroyed)
     */
    public void shutdown() {
        scheduler.shutdown();
        try {
            if (!scheduler.awaitTermination(5, TimeUnit.SECONDS)) {
                scheduler.shutdownNow();
            }
            logger.info("TokenService scheduler shutdown completed");
        } catch (InterruptedException e) {
            scheduler.shutdownNow();
            Thread.currentThread().interrupt();
        }
    }
}
