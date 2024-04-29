package com.group4.ApplicationTrackingSytem.security;

import com.group4.ApplicationTrackingSytem.security.filters.ApplicationTrackingAuthenticationFilter;
import com.group4.ApplicationTrackingSytem.security.filters.ApplicationTrackingAuthorizationFilter;
import com.group4.ApplicationTrackingSytem.security.manager.ApplicantTrackingAuthenticationManager;
import com.group4.ApplicationTrackingSytem.security.services.JwtService;
import com.group4.ApplicationTrackingSytem.security.utils.SecurityUtils;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;

import java.util.List;

@Configuration
@AllArgsConstructor
public class SecurityConfig {

    private final ApplicantTrackingAuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final ApplicationTrackingAuthorizationFilter authorizationFilter;



    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http.csrf(c->c.disable())
                .sessionManagement(c->c.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .cors(httpSecurityCorsConfigurer -> {
                    CorsConfiguration corsConfiguration = new CorsConfiguration();
                    corsConfiguration.setAllowedMethods(List.of("POST", "PUT", "GET"));
                    corsConfiguration.setAllowedOrigins(List.of("*"));
                })
                .addFilterAt(new ApplicationTrackingAuthenticationFilter(authenticationManager, jwtService), UsernamePasswordAuthenticationFilter.class)
                .addFilterBefore(authorizationFilter, ApplicationTrackingAuthenticationFilter.class)
                .authorizeHttpRequests(c->c.requestMatchers(getPublicEndpoints()).permitAll()
                                .anyRequest().authenticated()
                        //     .requestMatchers(HttpMethod.PUT, "/api/v1/auth", "/api/v1/auth/**").hasAnyAuthority(Authority.USER.name())
                        //   . requestMatchers(HttpMethod.GET,   "swagger-ui/index.html/").hasAnyAuthority(Authority.USER.name()))

//                .requestMatchers("/v2/api-docs",
//                        "/api-docs",
//                        "/configuration/ui",
//                        "/swagger-resources/**",
//                        "/configuration/security",
//                        "/swagger-ui/**",
//                        "/webjars/**",
//                        "/actuator/**",
//                        "/v1/verifyEmailId/**",
//                        "/v1/login",
//                        "/v1/register").permitAll()
//                .anyRequest().authenticated())
                )
                .build();
    }
    /**
     * get user by id ==> /api/v1/user/1
     * /api/v1/user != /api/v1/user/1
     * get all users ==> /api/v1/user?page=1&size=5 ==  /api/v1/user
     * */

    private static String[] getPublicEndpoints(){
        return SecurityUtils.getPublicEndpoints()
                .toArray(String[]::new);
    }

}
