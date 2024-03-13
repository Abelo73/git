package com.act.repomanagementsystem.controllers;

import com.act.repomanagementsystem.dto.AuthenticationRequest;
import com.act.repomanagementsystem.dto.AuthenticationResponse;
import com.act.repomanagementsystem.dto.RegisterRequest;
import com.act.repomanagementsystem.models.Gender;
import com.act.repomanagementsystem.services.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/auth")
//@CrossOrigin("*")
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(
            @RequestBody RegisterRequest request
    ){
        return ResponseEntity.ok(authenticationService.register(request));
    }

    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> register(
            @RequestBody AuthenticationRequest request
    ){
        return ResponseEntity.ok(authenticationService.authenticate(request));

    }

    @PostMapping("/addGender")
    public ResponseEntity<Gender> addGender(@RequestBody Gender gender){
        Gender savedGender = authenticationService.addGender(gender);
        return new ResponseEntity<>(savedGender, HttpStatus.CREATED);
    }
    @GetMapping("/getAllGenders")
    public ResponseEntity<List<Gender>> getAllGenders(){
        List<Gender> genderList = authenticationService.getAllGenders();
        return new ResponseEntity<>(genderList, HttpStatus.OK);
    }
}
