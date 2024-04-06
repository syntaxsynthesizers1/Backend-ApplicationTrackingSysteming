package com.group4.ApplicationTrackingSytem.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String email;
    private String password;
    @Enumerated
    private List<Authority> authorities;

    @OneToOne(cascade = CascadeType.ALL)
    private VerificationToken token;
    private boolean isEnabled = false;
    @OneToMany
    private List<Notification> notifications;

    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String gender;
    private LocalDateTime dob;
    private String NIC;
}
