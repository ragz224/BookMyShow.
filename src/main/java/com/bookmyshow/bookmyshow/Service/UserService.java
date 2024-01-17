package com.bookmyshow.bookmyshow.Service;

import com.bookmyshow.bookmyshow.Models.User;
import com.bookmyshow.bookmyshow.Repositories.UserRepository;
import lombok.Getter;
import lombok.Setter;
import org.aspectj.apache.bcel.classfile.Module;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Getter
@Setter
public class UserService {
    private UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User Signup(String email,  String name, String password) {
//        Optional<User> OpsUser = userRepository.findByEmail()

        User user = new User();
        user.setEmail(email);
        user.setName(name);
        user.setPassword(password);
//        return userRepository.save(user);
        User savedUserr = userRepository.save(user);

        return savedUserr;

    }


}
