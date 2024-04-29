package com.group4.ApplicationTrackingSytem.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.experimental.Accessors;

import java.util.Date;
import java.util.List;

@Data
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table (name = "ATS_JOB_OPENINGS_TABLE")
public class JobOpening {
    @Id
    @GeneratedValue
    private int jobId;
    private String jobTitle;
    private String status;
    private String summary;
    private String department;
    private Date datePosted;
    private String closingDate;
    private String description;
    private List<String> responsibilities;
    private List<String> qualifications;
    private String location;
    private String workMode;
    private String salaryRange;
    private String role;
}
