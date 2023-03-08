package com.vtb.payandsave.request.auth;

import com.vtb.payandsave.entity.card.Card;
import com.vtb.payandsave.entity.Target;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Data
public class SignupRequest {
    @NotBlank(message = "Имя пользователя не должно быть пустым!")
    private String username;
    @NotBlank(message = "Пароль не должен быть пустым!")
    private String password;
    @NotBlank(message = "Имя не должно быть пустым!")
    private String name;
    @NotBlank(message = "Фамлия не должна быть пустой!")
    private String surname;
    private List<Target> targets = new ArrayList<>();
    private List<Card> cards = new ArrayList<>();
}

