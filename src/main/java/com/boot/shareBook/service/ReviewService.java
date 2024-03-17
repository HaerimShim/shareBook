package com.boot.shareBook.service;

import com.boot.shareBook.model.Review;
import com.boot.shareBook.model.User;
import com.boot.shareBook.repository.ReviewRepository;
import com.boot.shareBook.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReviewService {
    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    private UserRepository userRepository;

     public Review save(String username, Review review) {
         User user = userRepository.findByUsername(username);
         review.setUser(user);
         return reviewRepository.save(review);
     }
}
