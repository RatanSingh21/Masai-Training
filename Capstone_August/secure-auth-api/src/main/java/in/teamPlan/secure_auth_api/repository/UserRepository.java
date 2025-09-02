package in.teamPlan.secure_auth_api.repository;

import in.teamPlan.secure_auth_api.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByUsername(String username);

    Optional<User> findByEmail(String email);

    Optional<User> findByUsernameOrEmail(String username, String email);

    boolean existsByUsername(String username);

    boolean existsByEmail(String email);

    @Modifying
    @Query("UPDATE User u SET u.lastLoginTime = :loginTime WHERE u.username = :username")
    void updateLastLoginTime(@Param("username") String username, @Param("loginTime") LocalDateTime loginTime);

    @Query("SELECT COUNT(u) FROM User u WHERE u.isActive = true")
    long countActiveUsers();
}
