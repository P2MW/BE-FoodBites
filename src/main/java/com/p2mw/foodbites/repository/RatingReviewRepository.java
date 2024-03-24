package com.p2mw.foodbites.repository;

import com.p2mw.foodbites.model.RatingReview;
import com.p2mw.foodbites.model.Seller;
import com.p2mw.foodbites.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RatingReviewRepository extends JpaRepository<RatingReview, Long> {
    List<RatingReview> findBySellerId(long sellerId);

    @Query("SELECT AVG(rr.rating) FROM RatingReview rr WHERE rr.seller.id = ?1")
    double calculateAverageRatingBySellerId(long sellerId);
}
