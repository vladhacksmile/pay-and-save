package com.vtb.payandsave.service;

import com.vtb.payandsave.entity.Account;
import com.vtb.payandsave.repository.TargetRepository;
import com.vtb.payandsave.request.TargetRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class TargetService {

    @Autowired
    TargetRepository targetRepository;

    public ResponseEntity<?> add(Account account, TargetRequest targetRequest) {
        return ResponseEntity.ok("test");
    }

}