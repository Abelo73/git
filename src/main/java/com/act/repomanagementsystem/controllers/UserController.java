package com.act.repomanagementsystem.controllers;


import com.act.repomanagementsystem.exceptions.UserNotFoundException;
import com.act.repomanagementsystem.models.User;
import com.act.repomanagementsystem.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/user")
//@CrossOrigin("*")
public class UserController {

    private final UserService userService;

    @GetMapping("/all")
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    // Endpoint to search for users by email
    @GetMapping("/search")
    public List<User> searchUsersByEmail(@RequestParam String email) {
        return userService.searchUsersByEmail(email);
    }
    // Endpoint to find a user by email
    @GetMapping("/findByEmail")
    public ResponseEntity<User> findUserByEmail(@RequestParam String email) throws UserNotFoundException {
        User user = userService.findUserByEmail(email);
        return ResponseEntity.ok().body(user);
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<String> deleteUser(@PathVariable Long userId) {
        boolean deleted = userService.deleteUser(userId);
        if (deleted) {
            return ResponseEntity.ok("User deleted successfully");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found with ID: " + userId);
        }
    }

    // Endpoint to update user data
    @PutMapping("/{userId}")
    @PreAuthorize("hasRole('ROLE_USER')") // Requires the user to have the 'ROLE_USER' role
    public ResponseEntity<User> updateUser(@PathVariable Long userId, @RequestBody User updatedUser) throws UserNotFoundException {
        User user = userService.updateUser(userId, updatedUser);
        return ResponseEntity.ok().body(user);
    }
}
