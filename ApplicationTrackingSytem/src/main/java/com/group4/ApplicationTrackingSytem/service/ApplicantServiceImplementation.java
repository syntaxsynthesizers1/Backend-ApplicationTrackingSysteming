package com.group4.ApplicationTrackingSytem.service;

import com.group4.ApplicationTrackingSytem.entity.Applicant;
import com.group4.ApplicationTrackingSytem.repository.ApplicantsRepository;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
//@RequiredArgsConstructor
//@NoArgsConstructor
public class ApplicantServiceImplementation implements ApplicantsService {

    @Autowired
    private ApplicantsRepository applicantsRepository;

    @Override
    public Applicant saveApplicant(Applicant applicant) {
        return applicantsRepository.save(applicant);
    }

    @Override
    public List<Applicant> saveApplicants(List<Applicant> applicants) {
        return applicantsRepository.saveAll(applicants);
    }

    @Override
    public List<Applicant> getApplicants() {
        return applicantsRepository.findAll();
    }

    @Override
    public Applicant getApplicantById(int id) {
        return applicantsRepository.findById(id).orElse(null);
    }

    @Override
    public String deleteApplicant(int id) {

      applicantsRepository.deleteById(id);
      return "deleted applicant with id "+id;
    }

    @Override
    public Applicant updateApplicant(Applicant applicant) {
        Applicant exisitingApplicant = applicantsRepository.findById(applicant.getId()).orElse(null);
        assert exisitingApplicant != null;
        exisitingApplicant.setName(applicant.getName());
        exisitingApplicant.setDateOfApplication(applicant.getDateOfApplication());
        exisitingApplicant.setMail(applicant.getMail());
        exisitingApplicant.setCountry(applicant.getCountry());
        exisitingApplicant.setPhoneNumber(applicant.getPhoneNumber());
        return applicantsRepository.save(exisitingApplicant);
    }


}
