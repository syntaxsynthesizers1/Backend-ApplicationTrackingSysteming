package com.group4.ApplicationTrackingSytem.security.filters;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.group4.ApplicationTrackingSytem.dto.request.LoginRequest;
import com.group4.ApplicationTrackingSytem.dto.response.LoginResponse;
import com.group4.ApplicationTrackingSytem.security.manager.ApplicantTrackingAuthenticationManager;
import com.group4.ApplicationTrackingSytem.security.services.JwtService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


import java.io.IOException;
import java.io.InputStream;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@AllArgsConstructor
@Slf4j
public class ApplicationTrackingAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
    private final ApplicantTrackingAuthenticationManager authenticationManager;
    private final JwtService jwtService;


    @Override
    public Authentication attemptAuthentication(HttpServletRequest request,
                                                HttpServletResponse response) throws AuthenticationException {
        ObjectMapper mapper = new ObjectMapper();

        try {
            //1. extract login credentials from the request
            InputStream inputStream = request.getInputStream();
            LoginRequest loginRequest =
                    mapper.readValue(inputStream, LoginRequest.class);
            //2. create an Authentication object that is not yet authenticated
            Authentication authentication =
                    new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword());
            //3. use the AuthenticationManager to authenticate
            // the authentication object from above
            Authentication authenticationResult =
                    authenticationManager.authenticate(authentication);

            if (authenticationResult.isAuthenticated()) {
                //4. put the authenticated authentication object in the security context
                SecurityContextHolder.getContext().setAuthentication(authenticationResult);
                return authenticationResult;
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request,
                                            HttpServletResponse response,
                                            FilterChain chain,
                                            Authentication authResult)
            throws IOException, ServletException {

        ObjectMapper mapper = new ObjectMapper();
        String token = jwtService.generateAccessToken(authResult.getPrincipal().toString());
        LoginResponse loginResponse = new LoginResponse(token);
        response.setContentType(APPLICATION_JSON_VALUE);
        mapper.writeValue(response.getOutputStream(), loginResponse);
    }

    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) throws IOException, ServletException {
        super.unsuccessfulAuthentication(request, response, failed);
    }
}
