package com.vtb.payandsave.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class JwtResponse {
    private Long id;
    private String token;
    private String username;
    private String name;
    private String surname;
}
