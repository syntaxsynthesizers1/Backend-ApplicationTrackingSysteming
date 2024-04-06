package com.group4.ApplicationTrackingSytem.repositories;

import com.group4.ApplicationTrackingSytem.entity.Applicant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ApplicantsRepository extends JpaRepository<Applicant, Integer> {

    @Query(value = "SELECT e FROM Applicant e WHERE e.applicantId = :applicantId")
    List<Applicant> findByApplicantId(@Param("applicantId") int applicantId);


}
