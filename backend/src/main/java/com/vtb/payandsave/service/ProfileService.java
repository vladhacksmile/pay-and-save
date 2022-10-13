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

    @Autowired
    AccountRepository accountRepository;

    public ResponseEntity<?> get(Account account) {
        ProfileResponse profileResponse = new ProfileResponse(account.getAccount_id(), account.getUsername(),
                account.getName(), account.getSurname(), account.getSuperPriorityTarget_id(),
                account.isUseCashback(), account.isEvenDistribution());
        return ResponseEntity.ok(profileResponse);
    }

    public ResponseEntity<?> update(Account account, ProfileRequest profileRequest) {
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

        if(needToUpdate) {
            accountRepository.save(account);
            return ResponseEntity.ok(new MessageResponse("Profile updated!"));
        }

        return ResponseEntity.ok(new MessageResponse("Nothing to update!"));
    }
}
