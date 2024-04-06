package com.group4.ApplicationTrackingSytem.repositories;

import com.group4.ApplicationTrackingSytem.entity.JobOpening;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JobOpeningsRepository extends JpaRepository<JobOpening, Integer> {
}
