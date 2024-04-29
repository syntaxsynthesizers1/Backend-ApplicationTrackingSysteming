package com.group4.ApplicationTrackingSytem.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table (name = "ATS_APPLICANTS_TABLE")
public class Applicant {
    @Id
    @GeneratedValue
    private int id;
    private String name;
    private String phoneNumber;
    private String gender;
    private String mail;
    private String role;
    private Date dateOfApplication;
    private String country;
    @OneToOne(cascade = CascadeType.ALL)
//    @JoinColumn(name = "resume_id")
    private Resume resume;
    private String userId;
}
