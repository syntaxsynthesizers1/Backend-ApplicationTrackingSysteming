package com.group4.ApplicationTrackingSytem.controller;

import com.group4.ApplicationTrackingSytem.entity.Applicant;
import com.group4.ApplicationTrackingSytem.entity.JobOpening;
import com.group4.ApplicationTrackingSytem.model.Response;
import com.group4.ApplicationTrackingSytem.service.ApplicantsService;
import com.group4.ApplicationTrackingSytem.service.JobOpeningsService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/job-opening")

public class JobOpeningController {

    private final JobOpeningsService jobOpeningsService;


    @PostMapping(value = "/add-opening", consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
          produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    private JobOpening saveOpening(@RequestBody JobOpening jobOpening ) {
        return jobOpeningsService.saveOpening(jobOpening);
    }

    @GetMapping("/get-openings")
    private ResponseEntity<Response> getJobOpenings() {
        return ResponseEntity.ok().body(jobOpeningsService.getJobOpenings());

    }
}
