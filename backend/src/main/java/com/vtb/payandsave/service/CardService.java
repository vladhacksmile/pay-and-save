package com.vtb.payandsave.service;

import com.vtb.payandsave.entity.Account;
import com.vtb.payandsave.entity.Card;
import com.vtb.payandsave.entity.CardTransaction;
import com.vtb.payandsave.repository.CardRepository;
import com.vtb.payandsave.repository.TransactionRepository;
import com.vtb.payandsave.request.CardRequest;
import com.vtb.payandsave.request.PayByCardRequest;
import com.vtb.payandsave.request.TargetReplenishmentRequest;
import com.vtb.payandsave.response.MessageResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
public class CardService {
    @Autowired
    CardRepository cardRepository;

    @Autowired
    TargetService targetService;

    @Autowired
    TransactionRepository transactionRepository;

    public ResponseEntity<?> add(Account account, CardRequest cardRequest) {
        Card card = new Card(cardRequest.getType(), cardRequest.getPaymentSystem(), 10000000F, account);
        cardRepository.save(card);
        return ResponseEntity.ok(new MessageResponse("Card created!"));
    }

    @Transactional
    public ResponseEntity<?> payByCard(Account account, PayByCardRequest payByCardRequest) {
        Optional<Card> cardById = cardRepository.findById(payByCardRequest.getCard_id());
        if(cardById.isPresent()) {
            Card card = cardById.get();
            if (account.getAccount_id().equals(card.getAccount().getAccount_id())) {
                if(card.getAmount() >= payByCardRequest.getAmount()) {
                    CardTransaction cardTransaction = new CardTransaction(card, payByCardRequest.getCategory(), payByCardRequest.getAmount());

                    if (cardTransaction.getRoundingAmount() != 0) {
                        card.setAmount(card.getAmount() - cardTransaction.getAmount() - cardTransaction.getRoundingAmount() + cardTransaction.getCashback());
                        targetService.allocateMoney(account, cardTransaction.getRoundingAmount());
                    } else {
                        card.setAmount(card.getAmount() - cardTransaction.getAmount() + cardTransaction.getCashback());
                    }

                    transactionRepository.save(cardTransaction);
                    cardRepository.save(card);

                    return ResponseEntity.ok(new MessageResponse("Transaction done!"));
                }
                return ResponseEntity.badRequest().body(new MessageResponse("Not enough money for transaction!"));
            } else {
                return ResponseEntity.badRequest().body(new MessageResponse("Permission denied!"));
            }
        } else {
            return ResponseEntity.badRequest().body(new MessageResponse("Card not found!"));
        }
    }

    /* public boolean replenishmentByCard(Account account, TargetReplenishmentRequest targetReplenishmentRequest) {
        Optional<Card> cardById = cardRepository.findById(targetReplenishmentRequest.getCard_id());
        if(cardById.isPresent()) {
            Card card = cardById.get();
            if (account.getAccount_id().equals(card.getAccount().getAccount_id())) {
                if(card.getAmount() >= targetReplenishmentRequest.getAmount()) {
                    CardTransaction cardTransaction = new CardTransaction(card, "Пополнение цели", targetReplenishmentRequest.getAmount());

                    transactionRepository.save(cardTransaction);
                    cardRepository.save(card);

                    return true;
                }
                return false;
            } else {
                return false;
            }
        } else {
            return false;
        }
    } */
}
