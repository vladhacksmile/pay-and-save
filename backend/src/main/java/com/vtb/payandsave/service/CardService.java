package com.vtb.payandsave.service;

import com.vtb.payandsave.entity.Account;
import com.vtb.payandsave.entity.Card;
import com.vtb.payandsave.entity.Transaction;
import com.vtb.payandsave.repository.CardRepository;
import com.vtb.payandsave.repository.TransactionRepository;
import com.vtb.payandsave.request.CardRequest;
import com.vtb.payandsave.request.PayByCardRequest;
import com.vtb.payandsave.response.MessageResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

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

    /**
     * Метод для округления трат
     * @param card карта, по которой прошел платеж
     * @param transaction транзакция, по которой нужно произвести округление
     * @return результат округления в большую сторону
     */
    public static float calculateRoundingByTransaction(Card card, Transaction transaction) {
        int roundingStep = card.getCardRoundingStep().getRoundingStep();
        float remainder = transaction.getAmount() % roundingStep;

        return remainder != 0 ? roundingStep - remainder : 0;
    }

    @Transactional
    public ResponseEntity<?> payByCard(Account account, PayByCardRequest payByCardRequest) {
        Card card = cardRepository.findById(payByCardRequest.getCard_id()).get();
        if(account.getAccount_id().equals(card.getAccount().getAccount_id()) && card.getAmount() >= payByCardRequest.getAmount()) {
            Transaction transaction = new Transaction(card, payByCardRequest.getCategory(), payByCardRequest.getAmount());
            transactionRepository.save(transaction);

            cardRepository.save(card);

            System.out.println("NEW TRANSACTION " + transaction);
            System.out.println("ROUNDING TRIGGERED " + calculateRoundingByTransaction(card, transaction));
            return ResponseEntity.ok(new MessageResponse("Transaction done! Rounded " + calculateRoundingByTransaction(card, transaction) + "rub"));
        }
        return ResponseEntity.badRequest().body(new MessageResponse("Permission denied!"));
    }
}
