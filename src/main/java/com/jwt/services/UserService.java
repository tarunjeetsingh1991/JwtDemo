package com.jwt.services;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.jwt.models.AppUser;
import com.jwt.repositories.UserRepository;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public List<AppUser> getUsers() {
        return userRepository.findAll();
    }

    public AppUser registerUser(String name, String email, String username, String rawPassword) {
        AppUser user = new AppUser();
        user.setUsername(name);
        user.setEmail(email);
        user.setUsername(username);
        user.setPassword(passwordEncoder.encode(rawPassword));
        return userRepository.save(user);
    }

    public AppUser getByEmail(String email) {
        return userRepository.findByEmail(email).orElse(null);
    }
}
