package com.boot.shareBook.controller;

import com.boot.shareBook.model.Review;
import com.boot.shareBook.model.User;
import com.boot.shareBook.service.UserService;
import com.boot.shareBook.validator.ReviewValidator;
import com.boot.shareBook.validator.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
public class UserCtrl {

    @Autowired
    private UserService userService;

    @Autowired
    private UserValidator userValidator;

    @GetMapping("/login")
    public String userLogin() {
        return "login";
    }

    @GetMapping("/signUp")
    public String userSignUp(Model model) {
        model.addAttribute("user", new User());
        return "signUp";
    }

    @PostMapping("/signUp")
    public String signUp(User user, BindingResult bindingResult) {
        userValidator.validate(user, bindingResult);
        if(bindingResult.hasErrors()){
            return "signUp";
        }
        userService.save(user);
        return "redirect:/";
    }
}