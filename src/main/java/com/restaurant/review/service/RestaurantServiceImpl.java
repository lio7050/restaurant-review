package com.restaurant.review.service;

import com.restaurant.review.model.Restaurant;
import com.restaurant.review.model.Review;
import com.restaurant.review.repository.RestaurantRepository;
import com.restaurant.review.repository.ReviewRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class RestaurantServiceImpl implements RestaurantService {

    private final RestaurantRepository restaurantRepository;
    private final ReviewRepository reviewRepository;

    public RestaurantServiceImpl(RestaurantRepository restaurantRepository, ReviewRepository reviewRepository) {
        this.restaurantRepository = restaurantRepository;
        this.reviewRepository = reviewRepository;
    }

    @Override
    public Restaurant create(Restaurant restaurant) {
        return restaurantRepository.save(restaurant);
    }

    @Override
    public Optional<Restaurant> getById(Long id) {
        return restaurantRepository.findById(id);
    }

    @Override
    public List<Restaurant> getAll() {
        return restaurantRepository.findAll();
    }

    @Override
    public double getAverageRating(Long restaurantId) {
        return reviewRepository.findByRestaurant(new Restaurant())
                .stream()
                .filter(r -> r.getStatus().name().equals("APPROVED"))
                .mapToInt(Review::getRating)
                .average()
                .orElse(0.0);
    }

    @Override
    public List<Restaurant> getTop3ByCuisine(String cuisineType) {
        return restaurantRepository.findTop3ByCuisineTypeOrderByReviewsRatingDesc(cuisineType);
    }
}
