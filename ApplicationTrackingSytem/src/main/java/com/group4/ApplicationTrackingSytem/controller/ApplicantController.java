package com.group4.ApplicationTrackingSytem.controller;

import com.group4.ApplicationTrackingSytem.entity.Applicant;
import com.group4.ApplicationTrackingSytem.service.ApplicantsService;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
//@NoArgsConstructor(force = true)
public class ApplicantController {

    @Autowired
    private final ApplicantsService applicantsService;

    @PostMapping("/add-applicant")
    private Applicant saveApplicant(@RequestBody Applicant applicant) {
        return applicantsService.saveApplicant(applicant);
    }


}
