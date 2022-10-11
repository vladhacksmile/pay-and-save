package com.vtb.payandsave.service;

import com.vtb.payandsave.entity.Account;
import com.vtb.payandsave.entity.Card;
import com.vtb.payandsave.entity.Target;
import com.vtb.payandsave.entity.Transaction;
import com.vtb.payandsave.repository.TargetRepository;
import com.vtb.payandsave.request.TargetRequest;
import com.vtb.payandsave.response.MessageResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class TargetService {

    @Autowired
    TargetRepository targetRepository;

    public ResponseEntity<?> add(Account account, TargetRequest targetRequest) {
        Target target = new Target(targetRequest.getIcon_id(), targetRequest.getName(), targetRequest.getAmount(), targetRequest.getPriority(), account);
        targetRepository.save(target);
        return ResponseEntity.ok(new MessageResponse("Target added!"));
    }

}