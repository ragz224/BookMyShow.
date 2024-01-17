package com.bookmyshow.bookmyshow.DTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SignupResponseDto {
    private ResponseStatus responseStatus;
    private Long UserId;
}
