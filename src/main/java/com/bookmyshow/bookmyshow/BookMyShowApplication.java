package com.bookmyshow.bookmyshow;

import com.bookmyshow.bookmyshow.Controllers.UserController;
import com.bookmyshow.bookmyshow.DTO.SignupRequestDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class BookMyShowApplication implements CommandLineRunner {

    @Autowired
    private UserController userController;

    public static void main(String[] args) {
        SpringApplication.run(BookMyShowApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        SignupRequestDto signupRequestDto = new SignupRequestDto();
        signupRequestDto.setEmail("rag4@gmail.com");
        signupRequestDto.setName("Ragav");
        signupRequestDto.setPassword("#nnnyyyy");

        userController.Signup(signupRequestDto);

    }
}
