package com.group4.ApplicationTrackingSytem.security.manager;


import com.group4.ApplicationTrackingSytem.exceptions.UserAuthenticationFailedException;
import com.group4.ApplicationTrackingSytem.security.provider.ApplicantTrackingAuthenticationProvider;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;


@AllArgsConstructor
@Component
public class ApplicantTrackingAuthenticationManager implements AuthenticationManager {
    private final ApplicantTrackingAuthenticationProvider authenticationProvider;
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        if (authenticationProvider.supports(authentication.getClass()))
            return authenticationProvider.authenticate(authentication);
        throw new UserAuthenticationFailedException("Unable to authenticate requests of this type");
    }
}