package com.group4.ApplicationTrackingSytem.service;

import com.group4.ApplicationTrackingSytem.dto.request.ChangePasswordRequest;
import com.group4.ApplicationTrackingSytem.dto.request.CreateUserRequest;
import com.group4.ApplicationTrackingSytem.dto.response.UserResponse;
import com.group4.ApplicationTrackingSytem.exceptions.ApplicationTrackingSystemException;
import com.group4.ApplicationTrackingSytem.globalDTO.Response;
import com.group4.ApplicationTrackingSytem.model.User;

import java.util.List;
import java.util.Optional;

public interface UserService {


    User getUserById(Long id) throws ApplicationTrackingSystemException;

    UserResponse getUserBy(Long id) throws ApplicationTrackingSystemException;

    List<UserResponse> getUsers(int page, int size);

    Optional<User> getUserBy(String username);
    User createUser (CreateUserRequest createUserRequest) throws ApplicationTrackingSystemException;
    User saveUser (User user);
    Response changePassword (ChangePasswordRequest changePasswordRequest) throws ApplicationTrackingSystemException;
}
