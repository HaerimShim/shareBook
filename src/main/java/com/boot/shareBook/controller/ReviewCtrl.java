package com.boot.shareBook.controller;

import com.boot.shareBook.model.Review;
import com.boot.shareBook.repository.ReviewRepository;
import com.boot.shareBook.validator.ReviewValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDate;
import java.util.List;

@Controller
@RequestMapping("/review")
public class ReviewCtrl {

    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    private ReviewValidator reviewValidator;

    @GetMapping("/list")
    public String getReviewList(Model model) {
        List<Review> reviews = reviewRepository.findAll();
        model.addAttribute("reviews", reviews);
        return "reviewList";
    }

    @GetMapping("/write")
    public String getReviewWrite(Model model) {
        model.addAttribute("review", new Review());
        return "reviewWrite";
    }

    @PostMapping("/write")
    public String postReviewWrite(@ModelAttribute Review review, BindingResult bindingResult) {
        reviewValidator.validate(review, bindingResult);
        if(bindingResult.hasErrors()){
            return "reviewWrite";
        }
        reviewRepository.save(review);
        return "redirect:/review/list";
    }

    @GetMapping("/read")
    public String getReviewRead(Model model, Long id) {
        Review review = reviewRepository.findById(id).orElse(null);
        model.addAttribute("review", review );
        return "reviewRead";
    }

    @GetMapping("/delete")
    public String deleteReview(Model model, Long id) {
        Review review = reviewRepository.findById(id).orElse(null);
        reviewRepository.delete(review);
        return "redirect:/review/list";
    }
}
