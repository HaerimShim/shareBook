package com.boot.shareBook.repository;

import com.boot.shareBook.model.Review;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewRepository extends JpaRepository <Review, Long> {

    Page<Review> findByTitleContainingOrContentContaining(String title, String content, Pageable pageable);
}
