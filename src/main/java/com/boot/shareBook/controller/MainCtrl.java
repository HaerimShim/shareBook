package com.boot.shareBook.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainCtrl {
    @GetMapping
    public String main(){
        return "main";
    }
}
