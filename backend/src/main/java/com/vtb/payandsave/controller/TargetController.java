package com.vtb.payandsave.controller;

import com.vtb.payandsave.entity.Account;
import com.vtb.payandsave.repository.TargetRepository;
import com.vtb.payandsave.request.TargetRequest;
import com.vtb.payandsave.service.TargetService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/targets")
@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
public class TargetController {
    private final TargetService targetService;

    public TargetController(TargetService targetService) {
        this.targetService = targetService;
    }

    @PostMapping
    public ResponseEntity<?> add(@AuthenticationPrincipal Account account, @RequestBody TargetRequest targetRequest) {
        return targetService.add(account, targetRequest);
    }

    @GetMapping
    public @ResponseBody Iterable<?> getAllTargets(@AuthenticationPrincipal Account account) {
        return account.getTargets();
    }

}