package com.vtb.payandsave.controller;

import com.vtb.payandsave.entity.Account;
import com.vtb.payandsave.request.ProfileRequest;
import com.vtb.payandsave.service.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/profile")
@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)

public class ProfileController {

    @Autowired
    ProfileService profileService;

    @GetMapping
    public ResponseEntity<?> getProfile(@AuthenticationPrincipal Account account) {
        return new ResponseEntity<>(profileService.get(account), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> updateProfile(@AuthenticationPrincipal Account account, @RequestBody ProfileRequest profileRequest) {
        return new ResponseEntity<>(profileService.update(account, profileRequest), HttpStatus.OK);
    }
}
