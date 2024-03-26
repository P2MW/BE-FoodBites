package com.p2mw.foodbites.controller;

import com.p2mw.foodbites.dto.response.RatingReviewResponse;
import com.p2mw.foodbites.model.RatingReview;
import com.p2mw.foodbites.service.RatingReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/seller")
public class RatingReviewController {

    @Autowired
    private RatingReviewService ratingReviewService;

    @PostMapping("/{sellerId}/rating")
    public ResponseEntity<RatingReviewResponse> rateAndReviewSeller(
            @PathVariable long sellerId,
            @RequestParam int rating,
            @RequestParam String review) {
        RatingReview ratingReview = ratingReviewService.rateAndReviewSeller(sellerId, rating, review);
        RatingReviewResponse response = new RatingReviewResponse(ratingReview);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }


    @GetMapping("/{sellerId}/average-rating")
    public ResponseEntity<Double> calculateAverageRating(@PathVariable long sellerId) {
        double averageRating = ratingReviewService.calculateAverageRating(sellerId);
        return new ResponseEntity<>(averageRating, HttpStatus.OK);
    }

    @GetMapping("/{sellerId}/allratings")
    public ResponseEntity<List<RatingReviewResponse>> getAllRatingReviews(@PathVariable long sellerId) {
        List<RatingReview> ratingReviews = ratingReviewService.getAllRatingReviews(sellerId);
        List<RatingReviewResponse> responseList = ratingReviews.stream()
                .map(RatingReviewResponse::new)
                .collect(Collectors.toList());
        return ResponseEntity.ok(responseList);
    }
}
