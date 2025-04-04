package com.restaurant.review.service;

import com.restaurant.review.model.Restaurant;

import java.util.List;
import java.util.Optional;

public interface RestaurantService {
    Restaurant create(Restaurant restaurant);
    Optional<Restaurant> getById(Long id);
    List<Restaurant> getAll();
    double getAverageRating(Long restaurantId);
    List<Restaurant> getTop3ByCuisine(String cuisineType);
}
