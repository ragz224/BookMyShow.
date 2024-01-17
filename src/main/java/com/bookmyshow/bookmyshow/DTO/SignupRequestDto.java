package com.bookmyshow.bookmyshow.DTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SignupRequestDto {

    private String email;
    private String name;
    private String password;

}
