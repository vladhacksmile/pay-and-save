package com.vtb.payandsave.service;

import com.vtb.payandsave.entity.*;
import com.vtb.payandsave.entity.card.Card;
import com.vtb.payandsave.entity.card.CardTransaction;
import com.vtb.payandsave.entity.savingAccount.SavingAccount;
import com.vtb.payandsave.entity.savingAccount.SavingAccountTransaction;
import com.vtb.payandsave.model.target.TargetAllocateMoneyType;
import com.vtb.payandsave.repository.*;
import com.vtb.payandsave.request.target.TargetReplenishmentRequest;
import com.vtb.payandsave.request.target.TargetRequest;
import com.vtb.payandsave.request.target.TargetWithdrawRequest;
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

    @Autowired
    CardRepository cardRepository;

    @Autowired
    TransactionRepository transactionRepository;

    private String getNameOfTransactionByTargetAllocateMoneyType(CardTransaction transaction, TargetAllocateMoneyType targetAllocateMoneyType) {
        return targetAllocateMoneyType == TargetAllocateMoneyType.ROUNDING ? "Округление трат" :
                (targetAllocateMoneyType == TargetAllocateMoneyType.CASHBACK ? "Кешбек за покупки" : "Процент на остаток");
    }

    @Transactional
    public void allocateMoney(Account account, CardTransaction transaction, TargetAllocateMoneyType targetAllocateMoneyType) {
        float money = targetAllocateMoneyType == TargetAllocateMoneyType.ROUNDING ? transaction.getRoundingAmount()
                : targetAllocateMoneyType == TargetAllocateMoneyType.CASHBACK ? transaction.getCashback() : transaction.getPercentageOnBalance();


        if(account.getSuperPriorityTarget_id() != null) {
            Optional<Target> targetById = targetRepository.findById(account.getSuperPriorityTarget_id());
            if(targetById.isPresent()) {
                Target target = targetById.get();
                if(target.getAccount().getAccount_id().equals(account.getAccount_id())) {
                    target.setSum(target.getSum() + money);
                    target.getSavingAccount().getSavingAccountTransactions().add(new SavingAccountTransaction(target.getSavingAccount(), getNameOfTransactionByTargetAllocateMoneyType(transaction, targetAllocateMoneyType), transaction.getCategory(), money));
                    targetRepository.save(target);
                } else {
                    System.err.println("Предотвращена попытка пополнить чужую цель!");
                }
            }
        } else {
            if (account.isEvenDistribution()) {
                float moneyPerTarget = money / account.getTargets().size();
                for (Target target : account.getTargets()) {
                    target.setSum(target.getSum() + moneyPerTarget);
                    target.getSavingAccount().getSavingAccountTransactions().add(new SavingAccountTransaction(target.getSavingAccount(), getNameOfTransactionByTargetAllocateMoneyType(transaction, targetAllocateMoneyType), transaction.getCategory(), money));
                }
                accountRepository.save(account);
            } else {
                int highPriority = 0;
                int middlePriority = 0;
                int lowPriority = 0;

                for (Target target : account.getTargets()) {
                    switch (target.getPriority()) {
                        case HIGH:
                            highPriority++;
                            break;
                        case MIDDLE:
                            middlePriority++;
                            break;
                        case LOW:
                            lowPriority++;
                            break;
                        default:
                            System.err.println("Обнаружена цель без приоритета!");
                            break;
                    }
                }

                float percentHighPriority;
                float percentMiddlePriority;
                float percentLowPriority;
                if (highPriority > 0 && middlePriority > 0 && lowPriority > 0) {
                    percentHighPriority = 50;
                    percentMiddlePriority = 30;
                    percentLowPriority = 20;
                } else if (highPriority == 0 && middlePriority > 0 && lowPriority > 0) {
                    percentHighPriority = 0;
                    percentMiddlePriority = 55;
                    percentLowPriority = 45;
                } else if (highPriority > 0 && middlePriority == 0 && lowPriority > 0) {
                    percentHighPriority = 65;
                    percentMiddlePriority = 0;
                    percentLowPriority = 35;
                } else if (highPriority > 0 && middlePriority > 0 && lowPriority == 0) {
                    percentHighPriority = 65;
                    percentMiddlePriority = 45;
                    percentLowPriority = 0;
                } else if (highPriority > 0 && middlePriority == 0 && lowPriority == 0) {
                    percentHighPriority = 100;
                    percentMiddlePriority = 0;
                    percentLowPriority = 0;
                } else if (highPriority == 0 && middlePriority > 0 && lowPriority == 0) {
                    percentHighPriority = 0;
                    percentMiddlePriority = 100;
                    percentLowPriority = 0;
                } else if (highPriority == 0 && middlePriority == 0 && lowPriority > 0) {
                    percentHighPriority = 0;
                    percentMiddlePriority = 0;
                    percentLowPriority = 100;
                } else {
                    // По идее мы сюда не должны попасть, но на всякий случай пусть будет
                    System.err.println("Возможно какой-то случай в распределениии процентов на цели не обработан!");
                    return;
                }

                float moneyPerHighPriorityTarget = ((money * percentHighPriority) / 100) / highPriority;
                float moneyPerMiddlePriorityTarget = ((money * percentMiddlePriority) / 100) / middlePriority;
                float moneyPerLowPriorityTarget = ((money * percentLowPriority) / 100) / lowPriority;

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
                            break;
                    }
                    target.setSum(target.getSum() + moneyPerTarget);
                    target.getSavingAccount().getSavingAccountTransactions().add(new SavingAccountTransaction(target.getSavingAccount(), getNameOfTransactionByTargetAllocateMoneyType(transaction, targetAllocateMoneyType), transaction.getCategory(), moneyPerTarget));
                    targetRepository.save(target);
                }
            }
        }
    }

    @Transactional
    public boolean operationByCard(Account account, Long card_id, String name, String category, Float amount, boolean positive) {
        // TODO это должно вызываться из сервиса карты, но из-за рекурсии пришлось вынести этот метод в сервис с целями
        Optional<Card> cardById = cardRepository.findById(card_id);
        if(cardById.isPresent()) {
            Card card = cardById.get();
            if (account.getAccount_id().equals(card.getAccount().getAccount_id())) {
                if(card.getAmount() >= amount || positive) {
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

   public MessageResponse replenishment(Account account, Target target, TargetReplenishmentRequest targetReplenishmentRequest) {
        if(operationByCard(account, targetReplenishmentRequest.getCard_id(), "Пополнение цели", "Пополнение счета", targetReplenishmentRequest.getAmount(), false)) {
            target.setSum(target.getSum() + targetReplenishmentRequest.getAmount());
            target.getSavingAccount().getSavingAccountTransactions().add(new SavingAccountTransaction(target.getSavingAccount(), "Пополнение цели", "Пополнение счета", targetReplenishmentRequest.getAmount()));
            targetRepository.save(target);
            return new MessageResponse("Target replenished!");
        } else {
            return new MessageResponse("Target not replenished! Check your balance or card id!");
        }
    }

    public MessageResponse withdraw(Account account, Target target, TargetWithdrawRequest targetWithdrawRequest) {
        if(targetWithdrawRequest.getAmount() <= 0) targetWithdrawRequest.setAmount(target.getSum());

        if(operationByCard(account, targetWithdrawRequest.getCard_id(), "Вывод накопления с цели", "Пополнение карты", targetWithdrawRequest.getAmount(), true)) {
            target.setSum(target.getSum() - targetWithdrawRequest.getAmount());
            target.getSavingAccount().getSavingAccountTransactions().add(new SavingAccountTransaction(target.getSavingAccount(), "Вывод накопления с цели", "Пополнение карты", targetWithdrawRequest.getAmount()));
            if(target.getSum() == 0) {
                target.getSavingAccount().setOpened(false);
            }
            targetRepository.save(target);
            return new MessageResponse("Money withdrawn from the target!" + (target.getSavingAccount().isOpened() ? "" : "Saving account was closed!"));
        } else {
            return new MessageResponse("Money withdrawn from the target! Check your amount or card id!");
        }
    }

    public MessageResponse add(Account account, TargetRequest targetRequest) {
        SavingAccount savingAccount = new SavingAccount(10);
        Target target = new Target(targetRequest.getIcon_id(), targetRequest.getName(), targetRequest.getAmount(), targetRequest.getPriority(), account, savingAccount);
        savingAccount.setTarget(target);
        targetRepository.save(target);
        processingSuperPriority(account, target, targetRequest);
        return new MessageResponse("Target added!");
    }

    private void processingSuperPriority(Account account, Target target, TargetRequest targetRequest) {
        // Обработка случая с супер-приоритетом
        if (targetRequest.isSuperPriority()) {
            if(account.getSuperPriorityTarget_id() == null || !account.getSuperPriorityTarget_id().equals(target.getTarget_id())) {
                account.setSuperPriorityTarget_id(target.getTarget_id());
                accountRepository.save(account);
            }
        } else {
            if(account.getSuperPriorityTarget_id() != null) {
                if(account.getSuperPriorityTarget_id().equals(target.getTarget_id())) {
                    account.setSuperPriorityTarget_id(null);
                    accountRepository.save(account);
                }
            }
        }
    }

    public MessageResponse update(Account account, Target target, TargetRequest targetRequest) {
        target.setIcon_id(targetRequest.getIcon_id());
        target.setName(targetRequest.getName());
        target.setAmount(targetRequest.getAmount());
        target.setPriority(targetRequest.getPriority());
        targetRepository.save(target);

        processingSuperPriority(account, target, targetRequest);

        return new MessageResponse("Target updated!");
    }

}