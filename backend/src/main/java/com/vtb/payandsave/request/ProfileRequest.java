package com.vtb.payandsave.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class ProfileRequest {
    private String name;
    private String surname;
    private boolean isUseCashBack;
    private boolean isEvenDistribution;
}
