package com.group4.ApplicationTrackingSytem.service;

import com.group4.ApplicationTrackingSytem.dto.ApplicationsData;
import com.group4.ApplicationTrackingSytem.dto.GetApplicant;
import com.group4.ApplicationTrackingSytem.entity.Applicant;
import com.group4.ApplicationTrackingSytem.model.Response;

import java.util.List;

public interface ApplicantsService {
    public Applicant saveApplicant(Applicant applicant);

    public List<Applicant> saveApplicants(List<Applicant> applicants);

    public List<Applicant> getApplicants();

    public Response getApplicantById(GetApplicant getApplicant);
    public ApplicationsData<Applicant> getApplications();



    public String deleteApplicant(int id);

    public Applicant updateApplicant(Applicant applicant);
}
