package com.boot.shareBook.controller;

import com.boot.shareBook.model.Recommend;
import com.boot.shareBook.model.Report;
import com.boot.shareBook.service.ReviewService;
import com.boot.shareBook.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/report")
public class ReportCtrl {

    @Autowired
    private UserService userService;

    @Autowired
    private ReviewService reviewService;

    @PostMapping()
    public void reportReview(@RequestBody Map<String, Object> requestData) {
        Long reviewId = Long.parseLong(requestData.get("reviewId").toString());
        Long loginId = Long.parseLong(requestData.get("loginId").toString());

        reviewService.report(reviewId, loginId);
    }

    @GetMapping("/getTotalReportCnt")
    public Long getTotalReportCnt(long reviewId) {
        List<Report> reportList = reviewService.getReportList(reviewId);

        long totalReportCnt = 0;
        for (Report report : reportList) {
            totalReportCnt += report.getReportCnt();
        }

        return totalReportCnt;
    }
}
