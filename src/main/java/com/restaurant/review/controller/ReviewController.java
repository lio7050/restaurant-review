package com.restaurant.review.controller;

import com.restaurant.review.dto.*;
import com.restaurant.review.model.Review;
import com.restaurant.review.service.ReviewService;
import jakarta.validation.Valid;
import org.springframework.data.domain.*;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/reviews")
public class ReviewController {

    private final ReviewService reviewService;

    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @PostMapping("/{restaurantId}")
    public ReviewDTO submitReview(@PathVariable Long restaurantId,
                                  @Valid @RequestBody CreateReviewRequest request) {
        Review review = new Review();
        review.setRating(request.getRating());
        review.setComment(request.getComment());
        review.setVisitDate(request.getVisitDate());
        return mapToDTO(reviewService.submitReview(restaurantId, review));
    }

    @GetMapping("/restaurant/{restaurantId}")
    public List<ReviewDTO> getByRestaurant(@PathVariable Long restaurantId) {
        return reviewService.getReviewsForRestaurant(restaurantId)
                .stream().map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    @GetMapping
    public Page<ReviewDTO> getByStatus(@RequestParam(defaultValue = "PENDING") String status,
                                       @RequestParam(defaultValue = "0") int page,
                                       @RequestParam(defaultValue = "10") int size) {
        return reviewService.getReviewsByStatus(status, PageRequest.of(page, size))
                .map(this::mapToDTO);
    }

    private ReviewDTO mapToDTO(Review review) {
        ReviewDTO dto = new ReviewDTO();
        dto.setId(review.getId());
        dto.setRating(review.getRating());
        dto.setComment(review.getComment());
        dto.setVisitDate(review.getVisitDate());
        dto.setStatus(review.getStatus());
        return dto;
    }
}
