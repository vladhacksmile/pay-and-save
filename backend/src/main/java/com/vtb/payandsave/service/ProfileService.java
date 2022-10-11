package com.vtb.payandsave.service;

import com.vtb.payandsave.entity.Account;
import com.vtb.payandsave.entity.Target;
import com.vtb.payandsave.repository.AccountRepository;
import com.vtb.payandsave.request.ProfileRequest;
import com.vtb.payandsave.request.TargetRequest;
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
        account.setName(profileRequest.getName());
        account.setSurname(profileRequest.getSurname());
        account.setUseCashback(profileRequest.isUseCashBack());
        account.setEvenDistribution(profileRequest.isEvenDistribution());
        accountRepository.save(account);
        return ResponseEntity.ok(new MessageResponse("Profile updated!"));
    }
}
