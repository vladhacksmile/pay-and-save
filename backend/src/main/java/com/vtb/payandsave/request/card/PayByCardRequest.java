package com.vtb.payandsave.request.card;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;

@AllArgsConstructor
@Getter
@Setter
@Data
public class PayByCardRequest {
    @NotBlank(message = "Название покупки не должно быть пустым!")
    private String name;
    @NotBlank(message = "Категория покупки не должна быть пустой!")
    private String category;
    @Positive(message = "Сумма покупки должна быть положительной!")
    private Float amount;
}
