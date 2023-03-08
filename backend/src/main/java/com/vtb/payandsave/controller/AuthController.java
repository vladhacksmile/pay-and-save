package com.vtb.payandsave.controller;

import com.vtb.payandsave.exception.ExceptionMessages;
import com.vtb.payandsave.request.auth.LoginRequest;
import com.vtb.payandsave.request.auth.SignupRequest;
import com.vtb.payandsave.response.JwtResponse;
import com.vtb.payandsave.service.AuthService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/account")
@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public ResponseEntity<?> authUser(@RequestBody LoginRequest loginRequest) {
        try {
            JwtResponse jwtResponse = authService.authUser(loginRequest);
            return new ResponseEntity<>(jwtResponse, HttpStatus.OK);
        } catch (BadCredentialsException e) {
            return new ResponseEntity<>(new JwtResponse(ExceptionMessages.INCORRECT_LOGIN), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody SignupRequest signupRequest) {
        return new ResponseEntity<>(authService.registerUser(signupRequest), HttpStatus.OK);
    }

}