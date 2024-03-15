package com.boot.shareBook.controller;

import com.boot.shareBook.model.User;
import com.boot.shareBook.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
public class UserCtrl {

    @Autowired
    private UserService userService;

    @GetMapping("/login")
    public String userLogin() {
        return "login";
    }

    @GetMapping("/signUp")
    public String userSignUp() {
        return "signUp";
    }

    @PostMapping("/signUp")
    public String signUp(User user) {
        userService.save(user);
        return "redirect:/";
    }
}