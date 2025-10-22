package com.jwt.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jwt.models.AppUser;
import com.jwt.services.UserService;

import java.util.List;

@RestController
public class HomeController {

    @Autowired
    private UserService userService;

    @GetMapping("/home/users")
    public List<AppUser> getUsers() {
        return userService.getUsers();
    }
}
