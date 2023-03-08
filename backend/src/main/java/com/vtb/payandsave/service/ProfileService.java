package com.vtb.payandsave.service;

import com.vtb.payandsave.entity.Account;
import com.vtb.payandsave.repository.AccountRepository;
import com.vtb.payandsave.request.ProfileRequest;
import com.vtb.payandsave.response.MessageResponse;
import com.vtb.payandsave.response.ProfileResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class ProfileService {

    final AccountRepository accountRepository;

    public ProfileService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public ProfileResponse get(Account account) {
        return new ProfileResponse(account.getAccount_id(), account.getUsername(),
                account.getName(), account.getSurname(), account.getSuperPriorityTarget_id(),
                account.isUseCashback(), account.isEvenDistribution(), account.isPercentageOnBalance());
    }

    public MessageResponse update(Account account, ProfileRequest profileRequest) {
        boolean needToUpdate = false;
        if(!account.getName().equals(profileRequest.getName())) {
            account.setName(profileRequest.getName());
            needToUpdate = true;
        }

        if(!account.getSurname().equals(profileRequest.getSurname())) {
            account.setSurname(profileRequest.getSurname());
            needToUpdate = true;
        }

        if(!account.isUseCashback() == profileRequest.isUseCashBack()) {
            account.setUseCashback(profileRequest.isUseCashBack());
            needToUpdate = true;
        }

        if(!account.isEvenDistribution() == profileRequest.isEvenDistribution()) {
            account.setEvenDistribution(profileRequest.isEvenDistribution());
            needToUpdate = true;
        }

        if(!account.isPercentageOnBalance() == profileRequest.isPercentageOnBalance()) {
            account.setPercentageOnBalance(profileRequest.isPercentageOnBalance());
            needToUpdate = true;
        }

        if(needToUpdate) {
            accountRepository.save(account);
            return new MessageResponse("Profile updated!");
        }

        return new MessageResponse("Nothing to update!");
    }
}
