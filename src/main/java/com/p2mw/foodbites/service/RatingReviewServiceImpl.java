package com.p2mw.foodbites.service;

import com.p2mw.foodbites.model.RatingReview;
import com.p2mw.foodbites.model.Seller;
import com.p2mw.foodbites.model.User;
import com.p2mw.foodbites.repository.RatingReviewRepository;
import com.p2mw.foodbites.repository.SellerRepository;
import com.p2mw.foodbites.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class RatingReviewServiceImpl implements RatingReviewService {

    @Autowired
    private RatingReviewRepository ratingReviewRepository;

    @Autowired
    private SellerRepository sellerRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    @Transactional
    public RatingReview rateAndReviewSeller(long sellerId, int rating, String review) {
        String userEmail = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = null;
        if (userEmail != null) {
            user = userRepository.findByEmail(userEmail);
        }

        Seller seller = sellerRepository.findById(sellerId)
                .orElseThrow(() -> new IllegalArgumentException("Seller not found with id: " + sellerId));

        RatingReview ratingReview = new RatingReview();
        ratingReview.setUser(user);
        ratingReview.setSeller(seller);
        ratingReview.setRating(rating);
        ratingReview.setReview(review);
        ratingReview.setDate(LocalDateTime.now());

        return ratingReviewRepository.save(ratingReview);
    }


    @Override
    @Transactional(readOnly = true)
    public double calculateAverageRating(long sellerId) {
        return ratingReviewRepository.calculateAverageRatingBySellerId(sellerId);
    }

    @Override
    @Transactional(readOnly = true)
    public List<RatingReview> getAllRatingReviews(long sellerId) {
        return ratingReviewRepository.findBySellerId(sellerId);
    }
}

