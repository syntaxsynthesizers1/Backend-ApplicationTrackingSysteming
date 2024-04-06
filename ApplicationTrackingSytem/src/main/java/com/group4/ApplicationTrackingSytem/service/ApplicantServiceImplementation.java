package com.group4.ApplicationTrackingSytem.service;

import com.group4.ApplicationTrackingSytem.dto.ApplicationsData;
import com.group4.ApplicationTrackingSytem.dto.GetApplicant;
import com.group4.ApplicationTrackingSytem.entity.Applicant;
import com.group4.ApplicationTrackingSytem.enums.ResponseCodes;
import com.group4.ApplicationTrackingSytem.model.Response;
import com.group4.ApplicationTrackingSytem.repositories.ApplicantsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service

public class ApplicantServiceImplementation implements ApplicantsService {

    @Autowired
    private ApplicantsRepository applicantsRepository;

    @Override
    public Applicant saveApplicant(Applicant applicant) {
        applicant.setDateOfApplication(new Date());
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
    public Response getApplicantById(GetApplicant getApplicant) {
        Response<Object> response = new Response<>();
        try {
            int id = getApplicant.getId();
            List<Applicant> applicants = applicantsRepository.findByApplicantId(id);
//           List<Applicant> applicant =  applicantsRepository.findAllById(Collections.singleton(getApplicant.getId()));
            response.setResponseMessage(ResponseCodes.SUCCESS.getDescription()).setResponseCode(ResponseCodes.SUCCESS.getCode()).setData(applicants);
        } catch (Exception ex) {
            response.setResponseCode(ResponseCodes.ERROR.getCode()).setResponseMessage("FAILED : " + ex);
        }
        return response;
    }

    @Override
    public ApplicationsData getApplications() {
        ApplicationsData<Object> applicationsData = new ApplicationsData();
        int numberOfMales = 0;
        int numberOfFemales = 0;
        try {
           List<Applicant> applications =  applicantsRepository.findAll();
           if (!applications.isEmpty()) {
               for (Applicant application : applications) {
                   if ("M".equalsIgnoreCase(application.getGender())) {
                       numberOfMales = numberOfMales + 1;
                   }
                   if ("F".equalsIgnoreCase(application.getGender())) {
                       numberOfFemales = numberOfFemales + 1;
                   }
               }
           }
           applicationsData.setTotalFemales(numberOfFemales).setTotalMales(numberOfMales)
          .setResponseMessage(ResponseCodes.SUCCESS.getDescription())
                   .setResponseCode(ResponseCodes.SUCCESS.getCode()).setData(applications)
                   .setCount(applications.size());

        } catch (Exception ex) {
            applicationsData.setResponseCode(ResponseCodes.ERROR.getCode()).setResponseMessage("FAILED : " + ex);
        }
        return applicationsData;
    }


    @Override
    public String deleteApplicant(int id) {

        applicantsRepository.deleteById(id);
        return "deleted applicant with id " + id;
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
