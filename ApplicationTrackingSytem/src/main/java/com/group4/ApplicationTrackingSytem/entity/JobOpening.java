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
    private Date datePosted;
    private String location;
    private String workMode;
    private String renumeration;
    private String role;
}
