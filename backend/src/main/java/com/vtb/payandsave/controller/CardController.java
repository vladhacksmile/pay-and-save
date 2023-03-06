package com.vtb.payandsave.controller;

import com.vtb.payandsave.entity.Account;
import com.vtb.payandsave.entity.card.Card;
import com.vtb.payandsave.exception.CardNotFoundException;
import com.vtb.payandsave.request.card.CardReplenishmentRequest;
import com.vtb.payandsave.request.card.CardRequest;
import com.vtb.payandsave.request.card.CardSettingsRequest;
import com.vtb.payandsave.request.card.PayByCardRequest;
import com.vtb.payandsave.service.CardService;
import org.springframework.http.HttpStatus;
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
        return new ResponseEntity<>(cardService.add(account, cardRequest), HttpStatus.OK);
    }

    @GetMapping
    public @ResponseBody Iterable<?> getAllCards(@AuthenticationPrincipal Account account) {
        return account.getCards();
    }

    @PostMapping("/{id}/replenish")
    public ResponseEntity<?> replenishCardById(@AuthenticationPrincipal Account account, @PathVariable long id, @RequestBody CardReplenishmentRequest cardReplenishmentRequest) {
        return new ResponseEntity<>(cardService.replenishCard(account, getCardById(account, id), cardReplenishmentRequest), HttpStatus.OK);
    }

    @PostMapping("/{id}/pay")
    public ResponseEntity<?> payByCard(@AuthenticationPrincipal Account account, @PathVariable long id, @RequestBody PayByCardRequest payByCardRequest) {
        return new ResponseEntity<>(cardService.payByCard(account, getCardById(account, id), payByCardRequest), HttpStatus.OK);
    }

    @PostMapping("/{id}/settings")
    public ResponseEntity<?> cardSettings(@AuthenticationPrincipal Account account, @PathVariable long id, @RequestBody CardSettingsRequest cardSettingsRequest) {
        return new ResponseEntity<>(cardService.cardSettings(account, getCardById(account, id), cardSettingsRequest), HttpStatus.OK);
    }

    @GetMapping("/{id}/settings")
    public ResponseEntity<?> cardSettings(@AuthenticationPrincipal Account account, @PathVariable long id) {
        return new ResponseEntity<>(cardService.getCardSettings(account, getCardById(account, id)), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public Card getCardById(@AuthenticationPrincipal Account account, @PathVariable long id) {
        return account.getCards().stream().filter(card -> card.getCard_id().equals(id)).findFirst().orElseThrow(CardNotFoundException::new);
    }
}
