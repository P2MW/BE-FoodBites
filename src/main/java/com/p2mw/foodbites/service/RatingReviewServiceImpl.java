package com.p2mw.foodbites.service;

import com.p2mw.foodbites.model.RatingReview;
import com.p2mw.foodbites.model.Seller;
import com.p2mw.foodbites.model.User;
import com.p2mw.foodbites.repository.RatingReviewRepository;
import com.p2mw.foodbites.repository.SellerRepository;
import com.p2mw.foodbites.repository.UserRepository;
import com.p2mw.foodbites.security.jwt.JwtTokenExtractor;
import com.p2mw.foodbites.security.jwt.JwtUtils;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.server.ResponseStatusException;

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

    @Autowired
    private JwtUtils jwtUtils;

    @Autowired
    private JwtTokenExtractor jwtTokenExtractor;

    @Override
    @Transactional
    public RatingReview rateAndReviewSeller(long sellerId, int rating, String review) {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();

        // Baca token dari cookie
        String jwtToken = jwtTokenExtractor.extractJwtTokenFromCookie(request);

        // Validasi token dan ambil email pengguna dari token
        String userEmail = jwtTokenExtractor.getEmailFromJwtToken(jwtToken);

        // Cari pengguna berdasarkan email
        User existingUser = userRepository.findByEmail(userEmail);
        if (existingUser == null) {
            throw new RuntimeException("User not found");
        }


        // Cari penjual berdasarkan ID
        Seller seller = sellerRepository.findById(sellerId)
                .orElseThrow(() -> new IllegalArgumentException("Seller not found with id: " + sellerId));

        // Buat objek RatingReview
        RatingReview ratingReview = new RatingReview();
        ratingReview.setUser(existingUser);
        ratingReview.setSeller(seller);
        ratingReview.setRating(rating);
        ratingReview.setReview(review);
        ratingReview.setDate(LocalDateTime.now());

        // Simpan rating dan ulasan
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

