package com.group4.ApplicationTrackingSytem.service;

import com.group4.ApplicationTrackingSytem.dto.request.ChangePasswordRequest;
import com.group4.ApplicationTrackingSytem.dto.request.CreateUserRequest;
import com.group4.ApplicationTrackingSytem.dto.response.UserResponse;
import com.group4.ApplicationTrackingSytem.exceptions.ApplicationTrackingSystemException;
import com.group4.ApplicationTrackingSytem.globalDTO.Response;
import com.group4.ApplicationTrackingSytem.model.User;
import com.group4.ApplicationTrackingSytem.model.VerificationToken;
import com.group4.ApplicationTrackingSytem.repositories.UserRepository;
import com.group4.ApplicationTrackingSytem.repositories.VerificationTokenRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.context.ApplicationContextException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static com.group4.ApplicationTrackingSytem.model.Authority.USER;

@Service
@RequiredArgsConstructor
@Slf4j
public class ATSUserRegistrationService implements UserService{
    private final UserRepository userRepository;

    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;
    private final VerificationTokenRepository tokenRepository;

    private final NotificationService notificationService;


    @Override
    @Transactional
    public User createUser(CreateUserRequest createUserRequest) throws ApplicationTrackingSystemException {
        // Check if the user already exists
        if (userRepository.findByEmail(createUserRequest.getEmail()).isPresent()) {
            throw new ApplicationTrackingSystemException("User with this email already exists");
        }

        // Encode the password before saving
        String encodedPassword = passwordEncoder.encode(createUserRequest.getPassword());

        // Create a new user
        User newUser = new User();
        newUser.setEmail(createUserRequest.getEmail());
        newUser.setPassword(encodedPassword);
        newUser.setFirstName(createUserRequest.getFirstName());
        newUser.setLastName(createUserRequest.getLastName());
        String token =  UUID.randomUUID().toString();
        VerificationToken verificationToken = new VerificationToken(token, newUser);
       tokenRepository.save(verificationToken);
//        log.info("ver token {}", token1);
//        log.info("ver token {}", verificationToken);
        newUser.setToken(verificationToken);
        newUser.setAuthorities(List.of(USER));

        return userRepository.save(newUser);
    }

    @Override
    public Response changePassword(ChangePasswordRequest changePasswordRequest) throws ApplicationTrackingSystemException {
        Optional<User> user = getUserBy(changePasswordRequest.getEmail());
        if (user.isEmpty()) {
            throw new ApplicationTrackingSystemException("User not found");
        }
        if (!user.get().getPassword().equals(changePasswordRequest.getOldPassword()) ) {
            throw new ApplicationTrackingSystemException("Invalid Password");
        }
        user.get().setPassword(changePasswordRequest.getNewPassword());

        return new Response("Password Changed Successfully");
    }

    @Override
    public User saveUser (User user) {
        return userRepository.save(user);
    }



    @Override
    public User getUserById(Long id) throws ApplicationTrackingSystemException {
        return userRepository.findById(id).orElseThrow(
                ()-> new ApplicationContextException(String.format("user with id %d not found", id))
        );
    }
    @Override
    public UserResponse getUserBy(Long id) throws ApplicationTrackingSystemException {
        User user = getUserById(id);
        return modelMapper.map(user, UserResponse.class);
    }

    @Override
    public List<UserResponse> getUsers(int page, int size) {
        Pageable pageable = PageRequest.of(page-1, size);
        Page<User> userPage = userRepository.findAll(pageable);
        List<User> users = userPage.getContent();
        log.info("users:: {}", users);
        return users.stream()
                .map(user->modelMapper.map(user, UserResponse.class))
                .toList();

    }

    @Override
    public Optional<User> getUserBy(String email) {
        return userRepository.findByEmail(email);
    }

    private void updatePassword(String email, String newPassword) throws ApplicationTrackingSystemException {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new ApplicationContextException("User not found with email: " + email));


        user.setPassword(passwordEncoder.encode(newPassword));
        userRepository.save(user);
    }

}
