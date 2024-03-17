package com.boot.shareBook.controller;

import com.boot.shareBook.model.Review;
import com.boot.shareBook.model.User;
import com.boot.shareBook.repository.ReviewRepository;
import com.boot.shareBook.service.ReviewService;
import com.boot.shareBook.service.UserService;
import com.boot.shareBook.validator.ReviewValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@Controller
@RequestMapping("/review")
public class ReviewCtrl {

    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    private ReviewValidator reviewValidator;

    @Autowired
    private UserService userService;

    @Autowired
    private ReviewService reviewService;


    @GetMapping("/list")
    public String getReviewList(Model model, @PageableDefault(size = 6) Pageable pageable, @RequestParam(required = false, defaultValue = "") String searchText) {
//        Page<Review> reviews = reviewRepository.findAll(pageable);
        Page<Review> reviews = reviewRepository.findByTitleContainingOrContentContaining(searchText, searchText, pageable);
        int startPage = Math.max(1, reviews.getPageable().getPageNumber() - 4);
        int endPage = Math.min(reviews.getTotalPages(), reviews.getPageable().getPageNumber() +4);

        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);
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

        // 로그인한 유저 정보
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        UserDetails userDetails = (UserDetails)principal;
        String username = userDetails.getUsername();

        reviewService.save(username, review);
        return "redirect:/review/list";
    }

    @GetMapping("/read")
    public String getReviewRead(Model model, Long id) {
        Review review = reviewRepository.findById(id).orElse(null);

        // 로그인한 유저 정보
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        UserDetails userDetails = (UserDetails)principal;
        String username = userDetails.getUsername();

        model.addAttribute("username", username);
        model.addAttribute("review", review);
        return "reviewRead";
    }

    @GetMapping("/delete")
    public String deleteReview(Model model, Long id) {
        Review review = reviewRepository.findById(id).orElse(null);
        reviewRepository.delete(review);
        return "redirect:/review/list";
    }
}
