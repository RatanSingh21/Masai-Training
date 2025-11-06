package in.ratansgh.securebankingAPI.repository;

import in.ratansgh.securebankingAPI.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepo extends JpaRepository<Long, User> {

    Optional<User> findByUsername(String username);

}
