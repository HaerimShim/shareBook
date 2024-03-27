package com.boot.shareBook.repository;

import com.boot.shareBook.model.Recommend;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RecommendRepository extends JpaRepository <Recommend, Long> {

    Recommend findByReviewIdAndLoginId(Long reviewId, Long loginId);

    Recommend findByReviewId(Long reviewId);

    List<Recommend> findByReviewId(long reviewId);
}
