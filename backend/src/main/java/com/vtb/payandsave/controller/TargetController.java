package com.vtb.payandsave.controller;

import com.vtb.payandsave.entity.Account;
import com.vtb.payandsave.entity.Target;
import com.vtb.payandsave.exception.TargetNotFoundException;
import com.vtb.payandsave.request.target.TargetReplenishmentRequest;
import com.vtb.payandsave.request.target.TargetRequest;
import com.vtb.payandsave.request.target.TargetWithdrawRequest;
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

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@AuthenticationPrincipal Account account, @PathVariable long id, @RequestBody TargetRequest targetRequest) {
        return targetService.update(account, getTargetById(account, id), targetRequest);
    }

    @PostMapping("/{id}/replenishment")
    public ResponseEntity<?> replenishment(@AuthenticationPrincipal Account account, @PathVariable long id, @RequestBody TargetReplenishmentRequest targetReplenishmentRequest) {
        return targetService.replenishment(account, getTargetById(account, id), targetReplenishmentRequest);
    }

    @PostMapping("/{id}/withdraw")
    public ResponseEntity<?> withdraw(@AuthenticationPrincipal Account account, @PathVariable long id, @RequestBody TargetWithdrawRequest targetWithdrawRequest) {
        return targetService.withdraw(account, getTargetById(account, id), targetWithdrawRequest);
    }

    @GetMapping("/{id}")
    public Target getTargetById(@AuthenticationPrincipal Account account, @PathVariable long id) {
        return account.getTargets().stream().filter(target -> target.getTarget_id().equals(id)).findFirst().orElseThrow(TargetNotFoundException::new);
    }
}