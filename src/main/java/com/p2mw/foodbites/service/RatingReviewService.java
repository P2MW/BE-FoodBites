package com.p2mw.foodbites.service;

import com.p2mw.foodbites.model.RatingReview;
import com.p2mw.foodbites.model.Seller;
import com.p2mw.foodbites.model.User;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface RatingReviewService {

    @Transactional
    RatingReview rateAndReviewSeller(long sellerId, int rating, String review);

    @Transactional(readOnly = true)
    double calculateAverageRating(long sellerId);

    @Transactional(readOnly = true)
    List<RatingReview> getAllRatingReviews(long sellerId);
}
