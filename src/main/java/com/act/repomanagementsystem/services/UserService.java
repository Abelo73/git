package com.act.repomanagementsystem.services;


import com.act.repomanagementsystem.exceptions.UserNotFoundException;
import com.act.repomanagementsystem.models.User;
import com.act.repomanagementsystem.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

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



//    public void deleteUser(Long userId) throws UserNotFoundException {
//        User existingUser = userRepository.findById(userId)
//                .orElseThrow(() -> new UserNotFoundException("User not found with ID: " + userId));
//
//        userRepository.delete(existingUser);
//
//    }

//    public boolean deleteUser(Long userId) {
//        Optional<User> optionalUser = userRepository.findById(userId);
//        if (optionalUser.isEmpty()) {
//            // User with the given ID does not exist, return a response indicating that the user is already deleted
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User already deleted with ID: " + userId).hasBody();
//        }
//
//        userRepository.deleteById(userId);
//        // Return a response indicating successful deletion
//        return ResponseEntity.ok("User deleted successfully").hasBody();
//    }

//    public void deleteUser(Long userId) throws UserNotFoundException {
//        Optional<User> optionalUser = userRepository.findById(userId);
//        if (optionalUser.isEmpty()) {
//            throw new UserNotFoundException("User not found with ID: " + userId);
//        }
//        userRepository.deleteById(userId);
//    }

    public boolean deleteUser(Long userId) {
        Optional<User> optionalUser = userRepository.findById(userId);
        if (optionalUser.isEmpty()) {
            return false; // User not found, deletion failed
        }
        userRepository.deleteById(userId);
        return true; // Deletion successful
    }





    public Optional<User> getById(Long userId) {
        User existingUser = userRepository.findById(userId)
                .orElseThrow(()->new UsernameNotFoundException("User not found"));
        return Optional.ofNullable(existingUser);

    }
    public User updateUser(Long userId, User updatedUser) throws UserNotFoundException {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("User not found with id: " + userId));

        // Update user properties with values from updatedUser
        user.setFirstName(updatedUser.getFirstName());
        user.setLastName(updatedUser.getLastName());
        user.setDepartment(updatedUser.getDepartment());
//        user.setEmail(updatedUser.getEmail());
//        user.setUsername(updatedUser.getUsername());
        user.setMobile(updatedUser.getMobile());
        user.setAge(updatedUser.getAge());
//        user.setPassword(updatedUser.getPassword());
        user.setPassword(passwordEncoder.encode(updatedUser.getPassword()));
        user.setTeamLeader(updatedUser.getTeamLeader());
        user.setGender(updatedUser.getGender());
        user.setRole(updatedUser.getRole());
        // Update other properties as needed

        // Save the updated user
        return userRepository.save(user);
    }


}
