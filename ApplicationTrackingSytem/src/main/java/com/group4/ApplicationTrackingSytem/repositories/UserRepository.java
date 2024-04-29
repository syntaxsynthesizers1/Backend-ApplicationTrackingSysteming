package com.group4.ApplicationTrackingSytem.repositories;

import com.group4.ApplicationTrackingSytem.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository <User, Long> {
    Optional<User> findByEmail(String email);
}
