package com.boot.shareBook.repository;

import com.boot.shareBook.model.Review;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewRepository extends JpaRepository <Review, Long> {

}
