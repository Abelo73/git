package com.act.repomanagementsystem.services;

import com.act.repomanagementsystem.dto.AuthenticationRequest;
import com.act.repomanagementsystem.dto.AuthenticationResponse;
import com.act.repomanagementsystem.dto.RegisterRequest;
import com.act.repomanagementsystem.exceptions.UserNotFoundException;
import com.act.repomanagementsystem.models.Gender;
import com.act.repomanagementsystem.models.Role;
import com.act.repomanagementsystem.repository.GenderRepository;
import com.act.repomanagementsystem.repository.UserRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
@RequiredArgsConstructor
public class AuthenticationService {



    private final UserRepository repository;
    private final GenderRepository genderRepository;


    private final PasswordEncoder passwordEncoder;

    private final JwtService jwtService;

    private final AuthenticationManager authenticationManager;

    public Gender addGender(Gender gender) {
        return genderRepository.save(gender);
    }

    public List<Gender> getAllGenders() {
        return genderRepository.findAll();
    }

    public AuthenticationResponse register(RegisterRequest request) {

        // Set the default registration date to the current date
        LocalDateTime defaultRegistrationDate = LocalDateTime.now();

        // Check if the email already exists
        if (repository.existsByEmail(request.getEmail())) {
            // Email is already in use
            return AuthenticationResponse.builder()
                    .status("error")
                    .message("Email is already in use. Please choose a different email.")
                    .build();
        }

        if (repository.existsByEmail(request.getUsername())){
//            Username is already in use

            return AuthenticationResponse.builder()
                    .status("error")
                    .message("Username is already in use. Please choose a different username")
                    .build();
        }

        var user = com.act.repomanagementsystem.models.User.builder()
                .firstName(request.getFirstName())
                .username(request.getUsername())
                .lastName(request.getLastName())
                .email(request.getEmail())
                .teamLeader(request.getTeamLeader())
                .department(request.getDepartment())
                .age(request.getAge())
                .mobile(request.getMobile())
//                .gender(request.getGender())
                .password(passwordEncoder.encode(request.getPassword()))
                .registrationDate(defaultRegistrationDate)
//                .role(request.getRole())
//                .role(request.getRole().isEmpty() ? "USER" : request.getRole())
                .role(request.getRole() == null ? Role.valueOf("USER") : request.getRole())
                .build();


        // Save the user object to the database
        repository.save(user);

        // Generate access and refresh tokens after saving the user
        var jwtToken = jwtService.generateToken(user);
        var refreshToken = jwtService.generateRefreshToken(user);

        return AuthenticationResponse.builder()
                .token(jwtToken)
                .refreshToken(refreshToken)
                .status("success")
                .message("User registered successfully.")
                .build();
    }
    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        // Authenticate user
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );

        // Retrieve user from the database
        var user = repository.findByEmail(request.getEmail())
                .orElseThrow();

        // Generate access token
        var jwtToken = jwtService.generateToken(user);
        var refreshToken = jwtService.generateRefreshToken(user);


        return AuthenticationResponse.builder()
                .token(jwtToken)
                .accessToken(jwtToken)
                .refreshToken(refreshToken)
                .message("login successfully!")
                .status("success")
                .build();
    }

    public AuthenticationResponse refreshToken(String refreshToken) {

        try {

            UserDetails userDetails = jwtService.getUserDetailsFromToken(refreshToken);
            if (userDetails != null) {
                String newAccessToken = jwtService.generateToken(userDetails);
                return AuthenticationResponse.builder()
                        .token(newAccessToken)
                        .accessToken(newAccessToken)
                        .message("Access token refreshed successfully!")
                        .status("success")
                        .build();
            } else {
                // Handle invalid or expired refresh token
                return AuthenticationResponse.builder()
                        .status("error")
                        .message("Invalid or expired refresh token.")
                        .build();
            }
        } catch (Exception e) {
            // Log the exception for debugging
            e.printStackTrace();
            // Return an error response
            return AuthenticationResponse.builder()
                    .status("error")
                    .message("An error occurred while refreshing token.")
                    .build();
        }
    }


    public List<com.act.repomanagementsystem.models.User> getAllUsers() {
        return repository.findAll();
    }


    


//    ====================================================

    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Logger logger = LoggerFactory.getLogger(AuthenticationService.class);

        try {
            // Retrieve user from the database based on the username
            com.act.repomanagementsystem.models.User user = repository.findByEmail(email)
                    .orElseThrow(() -> new UsernameNotFoundException("User not found with username: " + email));

            // Return a UserDetails object representing the user
            return org.springframework.security.core.userdetails.User.builder()
                    .username(user.getEmail())
                    .password(user.getPassword())
                    .roles(String.valueOf(user.getRole()))
                    .build();
        } catch (UsernameNotFoundException ex) {
            // Log the error message
            logger.error("User not found: {}", email);
            // Rethrow the exception to propagate it further
            throw ex;
        }
    }



//    public void deleteUser(Long id) throws UserNotFoundException {
//        Optional<com.act.repomanagementsystem.models.User> userOptional = repository.findById(id);
//        if (userOptional.isPresent()) {
//            repository.delete(userOptional.get());
//        } else {
//            throw new UserNotFoundException("User not found with ID: " + id);
//        }
//    }





}