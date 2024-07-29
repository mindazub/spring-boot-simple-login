package com.axub.demo_c.service;

import com.axub.demo_c.model.AppUser;
import com.axub.demo_c.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public AppUser registerUser(String username, String password) {
        if (userRepository.findByUsername(username) != null) {
            throw new RuntimeException("User already exists!");
        }
        AppUser user = new AppUser();
        user.setUsername(username);
        user.setPassword(passwordEncoder.encode(password));
        return userRepository.save(user);
    }
}
