package com.group4.ApplicationTrackingSytem.dto.request;

import lombok.Data;

@Data
public class ForgotPasswordRequest {
    private String newPassword;
    private String email;
}
