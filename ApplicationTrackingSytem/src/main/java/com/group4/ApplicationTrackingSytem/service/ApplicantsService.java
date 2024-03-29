package com.group4.ApplicationTrackingSytem.service;

import com.group4.ApplicationTrackingSytem.entity.Applicant;

import java.util.List;

public interface ApplicantsService {
    public Applicant saveApplicant(Applicant applicant);

    public List<Applicant> saveApplicants(List<Applicant> applicants);

    public List<Applicant> getApplicants();

    public Applicant getApplicantById(int id);

    public String deleteApplicant(int id);

    public Applicant updateApplicant(Applicant applicant);
}
