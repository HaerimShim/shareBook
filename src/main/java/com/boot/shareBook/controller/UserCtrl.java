package com.boot.shareBook.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
public class UserCtrl {
    @GetMapping("/login")
    public String userLogin(){
        return "login";
    }

    @GetMapping("/signUp")
    public String userSignUp(){
        return "signUp";
    }
}