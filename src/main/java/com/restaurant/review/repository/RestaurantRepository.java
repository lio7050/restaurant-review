package com.restaurant.review.repository;

import com.restaurant.review.model.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RestaurantRepository extends JpaRepository<Restaurant, Long> {
    @Query("""
                SELECT r FROM Restaurant r
                JOIN r.reviews rev
                WHERE (:cuisineType IS NULL OR r.cuisineType = :cuisineType)
                GROUP BY r
                ORDER BY AVG(rev.rating) DESC
            """)
    List<Restaurant> findTop3ByCuisineTypeOrderByReviewsRatingDesc(String cuisineType);
}
