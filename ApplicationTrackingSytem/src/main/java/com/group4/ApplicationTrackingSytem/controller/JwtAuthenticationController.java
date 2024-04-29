package com.group4.ApplicationTrackingSytem.controller;

import com.group4.ApplicationTrackingSytem.dto.request.AuthRequest;
import com.group4.ApplicationTrackingSytem.dto.request.ForgotPasswordRequest;
import com.group4.ApplicationTrackingSytem.dto.response.AuthResponse;
import com.group4.ApplicationTrackingSytem.dto.response.ValidateResponse;
import com.group4.ApplicationTrackingSytem.exceptions.ApplicationTrackingSystemException;
import com.group4.ApplicationTrackingSytem.globalDTO.Response;
import com.group4.ApplicationTrackingSytem.service.AuthService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
@AllArgsConstructor
public class JwtAuthenticationController {
    private final AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<AuthResponse> register(@Valid @RequestBody AuthRequest request) throws ApplicationTrackingSystemException {
        return ResponseEntity.status(HttpStatus.OK).body(authService.register(request));
    }
    @PostMapping("/forgot-password")
    public ResponseEntity<Response> forgotPassword(@Valid @RequestBody ForgotPasswordRequest forgotPasswordRequest) throws ApplicationTrackingSystemException {
        return ResponseEntity.status(HttpStatus.OK).body(authService.forgotPassword(forgotPasswordRequest));
    }

    @GetMapping("/verifyEmail")
    public ValidateResponse verifyEmail(@RequestParam("token") String token) throws ApplicationTrackingSystemException {
        return authService.verify(token);
    }
}
