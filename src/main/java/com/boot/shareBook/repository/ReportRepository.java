package com.boot.shareBook.repository;

import com.boot.shareBook.model.Recommend;
import com.boot.shareBook.model.Report;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReportRepository extends JpaRepository <Report, Long> {

    Report findByReviewIdAndLoginId(Long reviewId, Long loginId);

    List<Report> findByReviewId(long reviewId);
}
