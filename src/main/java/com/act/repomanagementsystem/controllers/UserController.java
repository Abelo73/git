package com.act.repomanagementsystem.controllers;


import com.act.repomanagementsystem.exceptions.UserNotFoundException;
import com.act.repomanagementsystem.models.User;
import com.act.repomanagementsystem.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
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
    // Endpoint to delete a user
    @DeleteMapping("/{userId}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long userId) throws UserNotFoundException {
        userService.deleteUser(userId);
        return ResponseEntity.noContent().build();
    }



}
