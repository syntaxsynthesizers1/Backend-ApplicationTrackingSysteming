package com.group4.ApplicationTrackingSytem.controller;

import com.group4.ApplicationTrackingSytem.dto.ApplicationsData;
import com.group4.ApplicationTrackingSytem.dto.GetApplicant;
import com.group4.ApplicationTrackingSytem.entity.Applicant;
import com.group4.ApplicationTrackingSytem.entity.JobOpening;
import com.group4.ApplicationTrackingSytem.model.Response;
import com.group4.ApplicationTrackingSytem.service.ApplicantsService;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ApplicantController {

    @Autowired
    private final ApplicantsService applicantsService;

    @PostMapping("/add-applicant")
    private Applicant saveApplicant(@RequestBody Applicant applicant) {
        return applicantsService.saveApplicant(applicant);
    }

    @PostMapping("/get-applicant-jobs")
    private ResponseEntity<Response> getApplicantById(@RequestBody GetApplicant getApplicant) {
        return ResponseEntity.ok().body(applicantsService.getApplicantById(getApplicant));

    }

    @GetMapping("/get-applications")
    private ResponseEntity<ApplicationsData<Applicant>> getApplications() {
        return ResponseEntity.ok().body(applicantsService.getApplications());

    }



}
