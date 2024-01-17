package com.bookmyshow.bookmyshow.Controllers;

import com.bookmyshow.bookmyshow.DTO.ResponseStatus;
import com.bookmyshow.bookmyshow.DTO.SignupRequestDto;
import com.bookmyshow.bookmyshow.DTO.SignupResponseDto;
import com.bookmyshow.bookmyshow.Models.User;
import com.bookmyshow.bookmyshow.Service.UserService;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
@Getter
@Setter
public class UserController {
    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    public SignupResponseDto Signup(SignupRequestDto signupRequestDto) {
        SignupResponseDto signupResponseDto = new SignupResponseDto();
        User user;

        try{
            user = userService.Signup(signupRequestDto.getEmail(),signupRequestDto.getName(),
                    signupRequestDto.getPassword());
            signupResponseDto.setResponseStatus(ResponseStatus.SUCESS);
            signupResponseDto.setUserId((long) user.getId());
        }
        catch(Exception exe) {
            signupResponseDto.setResponseStatus(ResponseStatus.FAILURE);
        }

        return  signupResponseDto;
    }


}
