package com.vtb.payandsave.service;

import com.vtb.payandsave.entity.Account;
import com.vtb.payandsave.entity.card.Card;
import com.vtb.payandsave.entity.card.CardTransaction;
import com.vtb.payandsave.model.target.TargetAllocateMoneyType;
import com.vtb.payandsave.repository.CardRepository;
import com.vtb.payandsave.repository.TransactionRepository;
import com.vtb.payandsave.request.card.CardReplenishmentRequest;
import com.vtb.payandsave.request.card.CardRequest;
import com.vtb.payandsave.request.card.CardSettingsRequest;
import com.vtb.payandsave.request.card.PayByCardRequest;
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
        Card card = new Card(cardRequest.getType(), cardRequest.getPaymentSystem(), account);
        cardRepository.save(card);
        return ResponseEntity.ok(new MessageResponse("Card created!"));
    }

    @Transactional
    public ResponseEntity<?> replenishCard(Account account, Card card, CardReplenishmentRequest cardReplenishmentRequest) {
        if(cardReplenishmentRequest.getAmount() > 0) {
            CardTransaction cardTransaction = new CardTransaction(card, "Пополнение карты", "Банковская операция", cardReplenishmentRequest.getAmount());
            card.setAmount(card.getAmount() + cardReplenishmentRequest.getAmount());
            transactionRepository.save(cardTransaction);
            cardRepository.save(card);

            return ResponseEntity.ok(new MessageResponse("Card replenished"));
        }
        return ResponseEntity.badRequest().body(new MessageResponse("Card didn't replenish! Amount must be positive!"));
    }

    @Transactional
    public ResponseEntity<?> payByCard(Account account, Card card, PayByCardRequest payByCardRequest) {
        if(card.isActive()) {
            if (card.getAmount() >= payByCardRequest.getAmount()) {
                CardTransaction cardTransaction = new CardTransaction(card, payByCardRequest.getName(), payByCardRequest.getCategory(), payByCardRequest.getAmount());

                if (cardTransaction.getRoundingAmount() != 0 && card.getAmount() >= cardTransaction.getAmount() + cardTransaction.getRoundingAmount()) {
                    card.setAmount(card.getAmount() - cardTransaction.getAmount() - cardTransaction.getRoundingAmount());
                    targetService.allocateMoney(account, cardTransaction, TargetAllocateMoneyType.ROUNDING);
                } else {
                    card.setAmount(card.getAmount() - cardTransaction.getAmount());
                }

                if (account.isUseCashback()) {
                    card.setAmount(card.getAmount() - cardTransaction.getCashback());
                    targetService.allocateMoney(account, cardTransaction, TargetAllocateMoneyType.CASHBACK);
                } else {
                    card.setAmount(card.getAmount() + cardTransaction.getCashback());
                }

                transactionRepository.save(cardTransaction);
                cardRepository.save(card);

                return ResponseEntity.ok(new MessageResponse("Transaction done!"));
            }
            return ResponseEntity.badRequest().body(new MessageResponse("Not enough money for transaction!"));
        } else {
            return ResponseEntity.badRequest().body(new MessageResponse("Sorry, but card with this id blocked!"));
        }
    }

    public ResponseEntity<?> cardSettings(Account account, Card card, CardSettingsRequest cardSettingsRequest) {
        boolean needToUpdate = false;

        if(card.getCardRoundingStep() != cardSettingsRequest.getRoundingStep()) {
            card.setCardRoundingStep(cardSettingsRequest.getRoundingStep());
            needToUpdate = true;
        }

        if(card.isActive() != cardSettingsRequest.isActive()) {
            card.setActive(cardSettingsRequest.isActive());
            needToUpdate = true;
        }

        if(needToUpdate) {
            cardRepository.save(card);
            if(!card.isActive()) {
                return ResponseEntity.ok(new MessageResponse("Settings for card saved! Card is blocking!"));
            } else {
                return ResponseEntity.ok(new MessageResponse("Settings for card saved! Card is active!"));
            }
        }

        return ResponseEntity.ok(new MessageResponse("Nothing to update card settings!"));
    }

    @Transactional
    public boolean operationByCard(Account account, Long card_id, String name, String category, Float amount) {
        Optional<Card> cardById = cardRepository.findById(card_id);
        if(cardById.isPresent()) {
            Card card = cardById.get();
            if (account.getAccount_id().equals(card.getAccount().getAccount_id())) {
                if(card.getAmount() >= amount) {
                    CardTransaction cardTransaction = new CardTransaction(card, name, category, amount);

                    transactionRepository.save(cardTransaction);
                    cardRepository.save(card);
                    return true;
                }
                return false;
            }
            return false;
        }
        return false;
    }
}
