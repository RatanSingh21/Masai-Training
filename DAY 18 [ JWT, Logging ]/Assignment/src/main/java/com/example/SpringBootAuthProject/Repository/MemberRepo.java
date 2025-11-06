package com.example.SpringBootAuthProject.Repository;

import com.example.SpringBootAuthProject.Entity.Member;
import jakarta.validation.constraints.NotEmpty;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MemberRepo extends JpaRepository<Member, Long> {


    @Query("SELECT u.userId FROM Member u")
    List<String> findAllUserIds();

    Member findByUserId(String userId);

    boolean existsByUserId(String userId);
}
