package com.group4.ApplicationTrackingSytem.service;

import com.group4.ApplicationTrackingSytem.dto.request.AuthRequest;
import com.group4.ApplicationTrackingSytem.dto.request.ForgotPasswordRequest;
import com.group4.ApplicationTrackingSytem.dto.response.AuthResponse;
import com.group4.ApplicationTrackingSytem.dto.response.ValidateResponse;
import com.group4.ApplicationTrackingSytem.exceptions.ApplicationTrackingSystemException;
import com.group4.ApplicationTrackingSytem.globalDTO.Response;

public interface AuthService {
    AuthResponse register(AuthRequest registerRequest) throws ApplicationTrackingSystemException;

    ValidateResponse verify(String token) throws ApplicationTrackingSystemException;
    Response forgotPassword (ForgotPasswordRequest forgotPasswordRequest) throws ApplicationTrackingSystemException;
}
