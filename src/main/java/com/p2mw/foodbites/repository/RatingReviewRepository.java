package com.p2mw.foodbites.repository;

import com.p2mw.foodbites.model.RatingReview;
import com.p2mw.foodbites.model.Seller;
import com.p2mw.foodbites.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RatingReviewRepository extends JpaRepository<RatingReview, Long> {
    List<RatingReview> findBySeller(Seller seller);
    List<RatingReview> findByUser(User user);
}
