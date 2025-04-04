package com.restaurant.review.service;


import com.restaurant.review.model.Review;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ReviewService {
    Review submitReview(Long restaurantId, Review review);
    List<Review> getReviewsForRestaurant(Long restaurantId);
    Page<Review> getReviewsByStatus(String status, Pageable pageable);
}
