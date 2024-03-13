package com.act.repomanagementsystem.services;


import com.act.repomanagementsystem.exceptions.UserNotFoundException;
import com.act.repomanagementsystem.models.User;
import com.act.repomanagementsystem.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    // Method to search for users by email
    public List<User> searchUsersByEmail(String email) {
        return userRepository.findByEmailContaining(email);
    }

//    public Optional<User> findUserByEmail(String email) {
//        return userRepository.findByEmail(email);
//    }

    // Method to find a user by email
    public User findUserByEmail(String email) throws UserNotFoundException {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new UserNotFoundException("User not found with email: " + email));
    }

    // Method to delete a user
    public void deleteUser(Long userId) throws UserNotFoundException {
        User existingUser = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("User not found with ID: " + userId));
        userRepository.delete(existingUser);
    }


}
