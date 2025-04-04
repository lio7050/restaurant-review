package com.restaurant.review.service;

import com.restaurant.review.model.*;
import com.restaurant.review.repository.RestaurantRepository;
import com.restaurant.review.repository.ReviewRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ReviewServiceImpl implements ReviewService {

    private final ReviewRepository reviewRepository;
    private final RestaurantRepository restaurantRepository;

    public ReviewServiceImpl(ReviewRepository reviewRepository, RestaurantRepository restaurantRepository) {
        this.reviewRepository = reviewRepository;
        this.restaurantRepository = restaurantRepository;
    }

    @Override
    public Review submitReview(Long restaurantId, Review review) {
        Restaurant restaurant = restaurantRepository.findById(restaurantId)
                .orElseThrow(() -> new NoSuchElementException("Restaurant not found"));
        review.setRestaurant(restaurant);
        review.setStatus(ReviewStatus.PENDING);
        return reviewRepository.save(review);
    }

    @Override
    public List<Review> getReviewsForRestaurant(Long restaurantId) {
        Restaurant restaurant = restaurantRepository.findById(restaurantId)
                .orElseThrow(() -> new NoSuchElementException("Restaurant not found"));
        return reviewRepository.findByRestaurant(restaurant);
    }

    @Override
    public Page<Review> getReviewsByStatus(String status, Pageable pageable) {
        ReviewStatus reviewStatus = ReviewStatus.valueOf(status.toUpperCase());
        return reviewRepository.findByStatus(reviewStatus, pageable);
    }
}
