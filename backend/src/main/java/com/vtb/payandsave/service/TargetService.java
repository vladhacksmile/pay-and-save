package com.vtb.payandsave.service;

import com.vtb.payandsave.entity.*;
import com.vtb.payandsave.repository.AccountRepository;
import com.vtb.payandsave.repository.SavingAccountRepository;
import com.vtb.payandsave.repository.TargetRepository;
import com.vtb.payandsave.request.TargetReplenishmentRequest;
import com.vtb.payandsave.request.TargetRequest;
import com.vtb.payandsave.response.MessageResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
public class TargetService {

    @Autowired
    TargetRepository targetRepository;

    @Autowired
    SavingAccountRepository savingAccountRepository;

    @Autowired
    AccountRepository accountRepository;

//    @Autowired
//    CardService cardService;

    @Transactional
    public void allocateMoney(Account account, float money) {
        if(account.isEvenDistribution()) {
            float moneyPerTarget = money / account.getTargets().size();
            for (Target target : account.getTargets()) {
                target.setSum(target.getSum() + moneyPerTarget);
            }
        } else {
            int highPriority = 0;
            int middlePriority = 0;
            int lowPriority = 0;

            for (Target target : account.getTargets()) {
                // Возможно не оптимальный алгоритм, но это первое что придумал
                switch (target.getPriority()) {
                    case HIGH:
                        highPriority++;
                        break;
                    case MIDDLE:
                        middlePriority++;
                    case LOW:
                        lowPriority++;
                    default:
                        System.err.println("Обнаружена цель без приоритета!");
                }
            }

            float percentHighPriority;
            float percentMiddlePriority;
            float percentLowPriority;
            if(highPriority > 0 && middlePriority > 0 && lowPriority > 0) {
                percentHighPriority = 50;
                percentMiddlePriority = 30;
                percentLowPriority = 20;
            } else if(highPriority == 0 && middlePriority > 0 && lowPriority > 0) {
                percentHighPriority = 0;
                percentMiddlePriority = 55;
                percentLowPriority = 45;
            } else if(highPriority > 0 && middlePriority == 0 && lowPriority > 0) {
                percentHighPriority = 65;
                percentMiddlePriority = 0;
                percentLowPriority = 35;
            } else if(highPriority > 0 && middlePriority > 0 && lowPriority == 0) {
                percentHighPriority = 65;
                percentMiddlePriority = 45;
                percentLowPriority = 0;
            } else if(highPriority > 0 && middlePriority == 0 && lowPriority == 0) {
                percentHighPriority = 100;
                percentMiddlePriority = 0;
                percentLowPriority = 0;
            } else if(highPriority == 0 && middlePriority > 0 && lowPriority == 0) {
                percentHighPriority = 0;
                percentMiddlePriority = 100;
                percentLowPriority = 0;
            } else if(highPriority == 0 && middlePriority == 0 && lowPriority > 0) {
                percentHighPriority = 0;
                percentMiddlePriority = 0;
                percentLowPriority = 100;
            } else if(highPriority == 0 && middlePriority == 0 && lowPriority == 0) {
                // По идее мы сюда не должны попасть, но на всякий случай пусть будет
                percentHighPriority = 0;
                percentMiddlePriority = 0;
                percentLowPriority = 0;
            } else {
                // По идее мы сюда не должны попасть, но на всякий случай пусть будет
                System.err.println("Возможно какой-то случай в распределениии процентов на цели не обработан!");
                return;
            }

            float moneyPerHighPriorityTarget = ((money * percentHighPriority) / 100) / account.getTargets().size();
            float moneyPerMiddlePriorityTarget = ((money * percentMiddlePriority) / 100) / account.getTargets().size();
            float moneyPerLowPriorityTarget = ((money * percentLowPriority) / 100) / account.getTargets().size();

            for (Target target : account.getTargets()) {
                float moneyPerTarget = 0;
                switch (target.getPriority()) {
                    case HIGH:
                        moneyPerTarget = moneyPerHighPriorityTarget;
                        break;
                    case MIDDLE:
                        moneyPerTarget = moneyPerMiddlePriorityTarget;
                        break;
                    case LOW:
                        moneyPerTarget = moneyPerLowPriorityTarget;
                }
                target.setSum(target.getSum() + moneyPerTarget);
                target.getSavingAccount().getSavingAccountTransactions().add(new SavingAccountTransaction(target.getSavingAccount(), "Какая-то транзакция", moneyPerTarget));
                targetRepository.save(target);
            }
        }
    }

   public ResponseEntity<?> replenishment(Account account, TargetReplenishmentRequest targetReplenishmentRequest) {
        Optional<Target> targetById = targetRepository.findById(targetReplenishmentRequest.getTarget_id());
        if(targetById.isPresent()) {
            Target target = targetById.get();
            // if(cardService.replenishmentByCard(account, targetReplenishmentRequest)) {
                target.setSum(target.getSum() + targetReplenishmentRequest.getAmount());
                targetRepository.save(target);
                return ResponseEntity.ok(new MessageResponse("Target replenished!"));
//            } else {
//                return ResponseEntity.badRequest().body(new MessageResponse("Target not replenished! Check your balance or check card id!"));
//            }
        }
        return ResponseEntity.badRequest().body(new MessageResponse("Target not found!"));
    }

    public ResponseEntity<?> add(Account account, TargetRequest targetRequest) {
        SavingAccount savingAccount = new SavingAccount(10);
        Target target = new Target(targetRequest.getIcon_id(), targetRequest.getName(), targetRequest.getAmount(), targetRequest.getPriority(), account, savingAccount);
        savingAccount.setTarget(target);
        targetRepository.save(target);
        return ResponseEntity.ok(new MessageResponse("Target added!"));
    }

    public ResponseEntity<?> update(Account account, TargetRequest targetRequest) {
        Target target = account.getTargets().get(targetRequest.getTarget_id());
        if(target != null) {
            target.setIcon_id(targetRequest.getIcon_id());
            target.setName(targetRequest.getName());
            target.setAmount(targetRequest.getAmount());
            target.setPriority(targetRequest.getPriority());
            targetRepository.save(target);
            return ResponseEntity.ok(new MessageResponse("Target updated!"));
        }
        return ResponseEntity.badRequest().body(new MessageResponse("Target not found!"));
    }

}