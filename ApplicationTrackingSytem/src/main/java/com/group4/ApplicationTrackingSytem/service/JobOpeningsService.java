package com.group4.ApplicationTrackingSytem.service;

import com.group4.ApplicationTrackingSytem.entity.Applicant;
import com.group4.ApplicationTrackingSytem.entity.JobOpening;
import com.group4.ApplicationTrackingSytem.model.Response;

import java.util.List;

public interface JobOpeningsService {
    public JobOpening saveOpening(JobOpening jobOpening);

//    public List<JobOpenings> saveApplicants(List<JobOpenings> applicants);

    public Response getJobOpenings();

    public JobOpening getOpeningById(int id);

    public String deleteOpening(int id);
    public JobOpening updateOpening(JobOpening jobOpening);

}
