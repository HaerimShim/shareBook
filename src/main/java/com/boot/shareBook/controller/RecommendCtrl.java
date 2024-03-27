package com.boot.shareBook.controller;

import com.boot.shareBook.model.Recommend;
import com.boot.shareBook.service.ReviewService;
import com.boot.shareBook.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/recommend")
public class RecommendCtrl {

    @Autowired
    private UserService userService;

    @Autowired
    private ReviewService reviewService;

    @PostMapping()
    public void recommendReview(Model model, @RequestBody Map<String, Object> requestData) {
        Long reviewId = Long.parseLong(requestData.get("reviewId").toString());
        Long loginId = Long.parseLong(requestData.get("loginId").toString());

        reviewService.recommend(reviewId, loginId);
    }

    @GetMapping("/getTotalRecommendCnt")
    public Long getTotalRecommendCnt(Model model, long reviewId) {
        List<Recommend> recommendList = reviewService.getRecommendList(reviewId);

        long totalRecommendCnt = 0;
        for (Recommend recommend : recommendList) {
            totalRecommendCnt += recommend.getRecommendCnt();
        }

        return totalRecommendCnt;
    }
}
