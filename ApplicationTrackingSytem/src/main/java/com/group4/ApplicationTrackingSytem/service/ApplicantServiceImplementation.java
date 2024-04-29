package com.group4.ApplicationTrackingSytem.service;

import com.group4.ApplicationTrackingSytem.dto.ApplicationsData;
import com.group4.ApplicationTrackingSytem.dto.GetApplicant;
import com.group4.ApplicationTrackingSytem.entity.Applicant;
import com.group4.ApplicationTrackingSytem.entity.Resume;
import com.group4.ApplicationTrackingSytem.enums.ResponseCodes;
import com.group4.ApplicationTrackingSytem.model.Response;
import com.group4.ApplicationTrackingSytem.repositories.ApplicantsRepository;
import com.group4.ApplicationTrackingSytem.repositories.ResumeRepository;
import lombok.extern.slf4j.Slf4j;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Date;
import java.util.List;

@Service
@Slf4j
public class ApplicantServiceImplementation implements ApplicantsService {

    @Autowired
    private ApplicantsRepository applicantsRepository;

    @Autowired
    private ResumeRepository resumeRepository;

    @Override
    public Applicant saveApplicant(Applicant applicant) {
        applicant.setDateOfApplication(new Date());
        Resume resume = new Resume();
        resume = applicant.getResume();
        resumeRepository.save(resume);
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
        log.info("inside the service");
        ApplicationsData<Object> applicationsData = new ApplicationsData();
        int numberOfMales = 0;
        int numberOfFemales = 0;
        try {
           List<Applicant> applications =  applicantsRepository.findAll();
           if (!applications.isEmpty()) {
               for (Applicant application : applications) {
                   if ("Male".equalsIgnoreCase(application.getGender())) {
                       numberOfMales = numberOfMales + 1;
                   }
                   if ("Female".equalsIgnoreCase(application.getGender())) {
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

    public String uploadFile (MultipartFile multipartFile,Applicant applicant) {
        if (!multipartFile.isEmpty()) {
            ApplicantsService applicantsService = new ApplicantServiceImplementation();
            try {
                // Load the PDF document
                PDDocument document = PDDocument.load(multipartFile.getInputStream());

                // Create PDFTextStripper object to extract text
                PDFTextStripper pdfStripper = new PDFTextStripper();

                // Extract text from the PDF document
                String content = pdfStripper.getText(document);

                // Keywords string separated by commas
                String keywordsString = "The,Content,Policy,header,helps,you,reduce,XSS,risks";
                // Split the keywords string into an array
                String[] keywords = keywordsString.split(",");

                // Initialize counter
                int count = 0;

                // Loop through each keyword
                for (String keyword : keywords) {
                    // Check if the content contains the keyword
                    if (content.toLowerCase().contains(keyword.trim().toLowerCase())) {
                        count++;
                    }
                }

                System.out.println("Total count of keywords found: " + count);

                // Close the PDF document
                document.close();

                if (count >= 5) {
                    applicantsService.saveApplicant(applicant);
                }
                return "File uploaded successfully";
            } catch (IOException e) {
                e.printStackTrace();
                return "Failed to upload file";
            }
        } else {
            return "File is empty";
        }
    }


}
