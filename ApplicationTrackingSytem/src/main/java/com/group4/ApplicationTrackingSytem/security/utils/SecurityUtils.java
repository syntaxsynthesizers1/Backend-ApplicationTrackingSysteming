package com.group4.ApplicationTrackingSytem.security.utils;

import java.util.List;

public class SecurityUtils {


    public static List<String> getPublicEndpoints(){
        return List.of(

//                "/api/v1/auth/verifyEmail",
//                        "/v/api-docs",
//                         "/api-docs",
                "/api/v1/user",
                "/api/v1/auth/**",

                "/api/v1/user",
                "/v2/api-docs",
        "/api-docs",
                "/configuration/ui",
                "/swagger-resources/**",
                "/configuration/security",
                "/swagger-ui/**",
                "/webjars/**",
                "/actuator/**"





        );

    }
}
