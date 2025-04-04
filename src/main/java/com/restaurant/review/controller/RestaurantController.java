package com.restaurant.review.controller;

import com.restaurant.review.dto.*;
import com.restaurant.review.model.Restaurant;
import com.restaurant.review.service.RestaurantService;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/restaurants")
public class RestaurantController {

    private final RestaurantService restaurantService;

    public RestaurantController(RestaurantService restaurantService) {
        this.restaurantService = restaurantService;
    }

    @PostMapping
    public RestaurantDTO createRestaurant(@Valid @RequestBody CreateRestaurantRequest request) {
        Restaurant saved = restaurantService.create(mapToEntity(request));
        return mapToDTO(saved);
    }

    @GetMapping
    public List<Restaurant> getAllRestaurants() {
        return restaurantService.getAll();
    }

    @GetMapping("/top")
    public List<Restaurant> getTopByCuisine(@RequestParam String cuisineType) {
        return restaurantService.getTop3ByCuisine(cuisineType);
    }

    private Restaurant mapToEntity(CreateRestaurantRequest request) {
        Restaurant r = new Restaurant();
        r.setName(request.getName());
        r.setCuisineType(request.getCuisineType());
        r.setAddress(request.getAddress());
        r.setPriceRange(request.getPriceRange());
        return r;
    }

    private RestaurantDTO mapToDTO(Restaurant r) {
        RestaurantDTO dto = new RestaurantDTO();
        dto.setId(r.getId());
        dto.setName(r.getName());
        dto.setCuisineType(r.getCuisineType());
        dto.setAddress(r.getAddress());
        dto.setPriceRange(r.getPriceRange());
        return dto;
    }
}
