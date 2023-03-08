package com.vtb.payandsave.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@AllArgsConstructor
@Getter
@Setter
@Data
public class ProfileRequest {
    @NotBlank(message = "Имя не должно быть пустым!")
    private String name;
    @NotBlank(message = "Фамлия не должна быть пустой!")
    private String surname;
    private boolean isUseCashBack;
    private boolean isEvenDistribution;
    private boolean isPercentageOnBalance;
}
