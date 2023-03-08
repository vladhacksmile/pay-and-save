package com.vtb.payandsave.request.auth;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@Data
public class LoginRequest {
    @NotBlank(message = "Имя пользователя не должно быть пустым!")
    private String username;
    @NotBlank(message = "Пароль не должен быть пустым!")
    private String password;
}

