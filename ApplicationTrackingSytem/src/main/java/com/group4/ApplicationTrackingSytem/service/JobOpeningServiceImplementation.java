package com.group4.ApplicationTrackingSytem.service;

import com.group4.ApplicationTrackingSytem.entity.JobOpening;
import com.group4.ApplicationTrackingSytem.enums.ResponseCodes;
import com.group4.ApplicationTrackingSytem.model.Response;
import com.group4.ApplicationTrackingSytem.repositories.JobOpeningsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class JobOpeningServiceImplementation implements JobOpeningsService {
    private final JobOpeningsRepository jobOpeningsRepository;

    @Override
    public JobOpening saveOpening(JobOpening jobOpening) {
        jobOpening.setDatePosted(new Date());
        return jobOpeningsRepository.save(jobOpening);
    }

//    @Override
//    public List<Applicant> saveApplicants(List<Applicant> applicants) {
//        return applicantsRepository.saveAll(applicants);
//    }

    @Override
    public Response getJobOpenings() {
        Response<Object> response = new Response<>();
        try {
            List<JobOpening> jobOpenings = jobOpeningsRepository.findAll();
            response.setResponseMessage(ResponseCodes.SUCCESS.getDescription()).setResponseCode(ResponseCodes.SUCCESS.getCode()).setData(jobOpenings);
        } catch (Exception ex) {
            response.setResponseCode(ResponseCodes.ERROR.getCode()).setResponseMessage("FAILED : " + ex);
        }
        return response;
    }



    @Override
    public JobOpening getOpeningById(int id) {
        return jobOpeningsRepository.findById(id).orElse(null);
    }

    @Override
    public String deleteOpening(int id) {

        jobOpeningsRepository.deleteById(id);
        return "deleted applicant with id " + id;
    }

    @Override
    public JobOpening updateOpening(JobOpening jobOpening) {
        JobOpening exisitingOpening = jobOpeningsRepository.findById(jobOpening.getJobId()).orElse(null);
        assert exisitingOpening != null;
        exisitingOpening.setLocation(jobOpening.getLocation())
                .setJobTitle(jobOpening.getJobTitle())
                .setStatus(jobOpening.getStatus())
                .setWorkMode(jobOpening.getWorkMode())
                .setRenumeration(jobOpening.getRenumeration());
        return jobOpeningsRepository.save(exisitingOpening);
    }

}
