package com.boot.shareBook.service;

import com.boot.shareBook.model.Recommend;
import com.boot.shareBook.model.Review;
import com.boot.shareBook.model.User;
import com.boot.shareBook.repository.RecommendRepository;
import com.boot.shareBook.repository.ReviewRepository;
import com.boot.shareBook.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewService {
    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RecommendRepository recommendRepository;

     public Review save(String username, Review review) {
         User user = userRepository.findByUsername(username);
         review.setUser(user);
         return reviewRepository.save(review);
     }

    public void recommend(Long reviewId, Long loginId) {
        Recommend recommendCheck = recommendRepository.findByReviewIdAndLoginId(reviewId, loginId);

        if(recommendCheck == null) {
            Recommend recommend = new Recommend();
            recommend.setReviewId(reviewId);
            recommend.setLoginId(loginId);
            recommend.setRecommendCnt(1);
            recommendRepository.save(recommend);
        } else {
            recommendRepository.delete(recommendCheck);
        }
    }

    public long getTotalRecommendCnt(Long reviewId) {
        Recommend recommendCheck = recommendRepository.findByReviewId(reviewId);
        if(recommendCheck != null) {
            return recommendCheck.getRecommendCnt();
        } else {
            return 0;
        }
    }

    public List<Recommend> getRecommendList(long reviewId) {
        return recommendRepository.findByReviewId(reviewId);
    }
}
