package com.act.repomanagementsystem.dto;

import com.act.repomanagementsystem.models.Gender;
import com.act.repomanagementsystem.models.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RegisterRequest {
    private String firstName;
    private String lastName;
    private String username;
    private String email;
    private String mobile;
    private String teamLeader;
    private String department;
    private Role role;
    private String password;
    private String confirmPassword;
    private boolean remember;
    private Integer age;
    private int gender; // Assuming gender will be represented by an integer (e.g., 1 for Male, 2 for Female)
}