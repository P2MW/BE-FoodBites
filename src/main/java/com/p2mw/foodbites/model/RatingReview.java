package com.p2mw.foodbites.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class RatingReview {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(referencedColumnName = "id")
    private User user;

    @ManyToOne
    @JoinColumn(referencedColumnName = "id")
    private Seller seller;

    private int rating;
    private String review;
    private LocalDateTime date;
}
