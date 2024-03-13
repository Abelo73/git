package com.act.repomanagementsystem.repository;

import com.act.repomanagementsystem.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);

    boolean existsByEmail(String email);


//    Optional<Object> findByUsernameOrEmail(String username, String email);
//    Optional<User> findByUsernameOrEmail(String username, String email);

    boolean existsByUsername(String username);

    List<User> findByEmailContaining(String email);
}
