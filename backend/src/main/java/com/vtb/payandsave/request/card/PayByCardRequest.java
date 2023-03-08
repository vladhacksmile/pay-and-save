package com.vtb.payandsave.request.card;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

@AllArgsConstructor
@Getter
@Setter
@Data
public class PayByCardRequest {
    @NotBlank(message = "Название покупки не должно быть пустым!")
    private String name;
    @NotBlank(message = "Категория покупки не должна быть пустой!")
    private String category;
    @Min(value = 1, message = "Сумма покупки должна быть минимум 1!")
    private Float amount;
}
