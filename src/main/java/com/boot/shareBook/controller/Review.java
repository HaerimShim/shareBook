package com.boot.shareBook.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/review")
public class Review {
    @GetMapping("/list")
    public String getReviewList(){
        return "reviewList";
    }
}
