package com.linkedin.social.repository;

import com.linkedin.social.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UsersRepository extends JpaRepository<Users, Long> {

    boolean existsByUsername(String username);

    Optional<Users> findByUsername(String username);

    List<Users> findByUsernameContainingIgnoreCase(String username);
}
