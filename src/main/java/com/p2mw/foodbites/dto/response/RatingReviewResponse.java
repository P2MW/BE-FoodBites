package com.p2mw.foodbites.dto.response;

import com.p2mw.foodbites.model.RatingReview;
import com.p2mw.foodbites.model.User;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class RatingReviewResponse {
    private Long id;
    private int rating;
    private String review;
    private LocalDateTime date;
    private String userName;

    public RatingReviewResponse(RatingReview ratingReview){
        this.id = ratingReview.getId();
        this.rating = ratingReview.getRating();
        this.review = ratingReview.getReview();
        this.date = ratingReview.getDate();
        if (ratingReview.getUser() != null) {
            this.userName = ratingReview.getUser().getFullName();
        }
    }
}
