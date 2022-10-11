package com.vtb.payandsave.controller;

import com.vtb.payandsave.entity.Account;
import com.vtb.payandsave.entity.Card;
import com.vtb.payandsave.request.CardRequest;
import com.vtb.payandsave.request.PayByCardRequest;
import com.vtb.payandsave.service.CardService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/cards")
@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
public class CardController {
    private final CardService cardService;

    public CardController(CardService cardService) {
        this.cardService = cardService;
    }

    @PostMapping
    public ResponseEntity<?> add(@AuthenticationPrincipal Account account, @RequestBody CardRequest cardRequest) {
        return cardService.add(account, cardRequest);
    }

    @GetMapping
    public @ResponseBody Iterable<?> getAllCards(@AuthenticationPrincipal Account account) {
        return account.getCards();
    }

    @PostMapping("/pay")
    public ResponseEntity<?> payByCard(@AuthenticationPrincipal Account account, @RequestBody PayByCardRequest payByCardRequest) {
        return cardService.payByCard(account, payByCardRequest);
    }
}
