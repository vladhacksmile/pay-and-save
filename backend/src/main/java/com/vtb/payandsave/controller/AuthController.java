package com.vtb.payandsave.controller;

import com.vtb.payandsave.request.LoginRequest;
import com.vtb.payandsave.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/account")
@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
public class AuthController {

    @Autowired
    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public ResponseEntity<?> authUser(@RequestBody LoginRequest loginRequest) {
        return authService.authUser(loginRequest);
    }

    @GetMapping("/login")
    public ResponseEntity<?> authUserTest() {
        return ResponseEntity.ok("test is working get");
    }

}