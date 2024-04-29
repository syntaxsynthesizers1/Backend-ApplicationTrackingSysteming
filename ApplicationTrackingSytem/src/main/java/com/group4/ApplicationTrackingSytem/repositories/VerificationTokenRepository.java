package com.group4.ApplicationTrackingSytem.repositories;

import com.group4.ApplicationTrackingSytem.model.VerificationToken;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface VerificationTokenRepository extends JpaRepository<VerificationToken, Long> {

    Optional<VerificationToken> findVerificationTokensByToken(String token);
}
