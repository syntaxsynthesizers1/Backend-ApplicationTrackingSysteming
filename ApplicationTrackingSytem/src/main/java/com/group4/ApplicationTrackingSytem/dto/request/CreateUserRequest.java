package com.group4.ApplicationTrackingSytem.dto.request;

import lombok.Data;

@Data
public class CreateUserRequest {
    private String email;
    private String password;
    private String firstName;
    private String lastName;
}
