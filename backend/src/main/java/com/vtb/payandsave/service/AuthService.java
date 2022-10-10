package com.vtb.payandsave.service;

import com.vtb.payandsave.request.LoginRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

@Service
public class AuthService {
    public ResponseEntity<?> authUser(LoginRequest loginRequest) {
        return ResponseEntity.ok("Authorized");
    }
}
