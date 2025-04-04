package com.restaurant.review.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.restaurant.review.model.Restaurant;
import com.restaurant.review.repository.RestaurantRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.*;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class RestaurantControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private RestaurantRepository restaurantRepository;

    @BeforeEach
    void setup() {
        restaurantRepository.deleteAll(); // Ensure clean state for each test
    }

    @Test
    @WithMockUser(username = "admin", roles = {"ADMIN"})
    void createRestaurant_ReturnsOk() throws Exception {
        Restaurant restaurant = new Restaurant();
        restaurant.setName("Testaurant");
        restaurant.setCuisineType("Fusion");
        restaurant.setAddress("123 Mockingbird Ln");

        mockMvc.perform(post("/api/restaurants")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(restaurant)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Testaurant"))
                .andExpect(jsonPath("$.cuisineType").value("Fusion"))
                .andExpect(jsonPath("$.address").value("123 Mockingbird Ln"));
    }
}
