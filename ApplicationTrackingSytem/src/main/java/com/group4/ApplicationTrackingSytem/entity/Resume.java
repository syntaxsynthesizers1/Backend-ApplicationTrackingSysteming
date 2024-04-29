package com.group4.ApplicationTrackingSytem.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "ATS_RESUME_TABLE")
public class Resume {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String dateOfBirth;
    private String country;
    private int yearsOfExperience;
    private int atsScore;
    private String information;
    private List<String> previousCompany;
    private List<String> previousRole;
    private List<String> years;
    private List<String> schools;
    private List<String> skills;
    private List<String> socialMedia;



}
