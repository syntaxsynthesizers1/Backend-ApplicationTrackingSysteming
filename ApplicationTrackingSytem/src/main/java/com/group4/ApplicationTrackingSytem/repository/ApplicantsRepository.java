package com.group4.ApplicationTrackingSytem.repository;

import com.group4.ApplicationTrackingSytem.entity.Applicant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

//@Repository
public interface ApplicantsRepository extends JpaRepository<Applicant, Integer> {

}
