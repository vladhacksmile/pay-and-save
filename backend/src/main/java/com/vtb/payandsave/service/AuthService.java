package com.vtb.payandsave.service;

import com.vtb.payandsave.entity.Account;
import com.vtb.payandsave.jwt.JwtUtils;
import com.vtb.payandsave.repository.AccountRepository;
import com.vtb.payandsave.request.auth.LoginRequest;
import com.vtb.payandsave.request.auth.SignupRequest;
import com.vtb.payandsave.response.JwtResponse;
import com.vtb.payandsave.response.MessageResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

@Service
public class AuthService {
    final AuthenticationManager authenticationManager;

    final AccountRepository accountRepository;

    final PasswordEncoder passwordEncoder;

    final JwtUtils jwtUtils;

    public AuthService(AuthenticationManager authenticationManager, AccountRepository accountRepository, PasswordEncoder passwordEncoder, JwtUtils jwtUtils) {
        this.authenticationManager = authenticationManager;
        this.accountRepository = accountRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtils = jwtUtils;
    }

    public JwtResponse authUser(LoginRequest loginRequest) {
        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(
                        loginRequest.getUsername(),
                        loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        Account userDetails = (Account) authentication.getPrincipal();

        return new JwtResponse(userDetails.getAccount_id(), jwt, userDetails.getUsername(), userDetails.getName(), userDetails.getSurname());
    }

    public MessageResponse registerUser(@RequestBody SignupRequest signupRequest) {
        if (accountRepository.existsByUsername(signupRequest.getUsername())) {
            return new MessageResponse("Phone number already exists!");
        }

        Account account = new Account(signupRequest.getUsername(), passwordEncoder.encode(signupRequest.getPassword()));

        accountRepository.save(account);
        return new MessageResponse("Phone number registered!");
    }
}
