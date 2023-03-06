package com.vtb.payandsave.request.auth;

import com.vtb.payandsave.entity.card.Card;
import com.vtb.payandsave.entity.Target;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Data
public class SignupRequest {
    private String username;
    private String password;
    private List<Target> targets = new ArrayList<>();
    private List<Card> cards = new ArrayList<>();
}

