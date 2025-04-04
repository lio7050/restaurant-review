package com.restaurant.review.repository;

import com.restaurant.review.model.Review;
import com.restaurant.review.model.Restaurant;
import com.restaurant.review.model.ReviewStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Long> {
    List<Review> findByRestaurant(Restaurant restaurant);

    Page<Review> findByStatus(ReviewStatus status, Pageable pageable);
}