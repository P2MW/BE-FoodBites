package com.p2mw.foodbites.service;

import com.p2mw.foodbites.dto.request.LoginRequest;
import com.p2mw.foodbites.dto.request.SellerSignupRequest;
import com.p2mw.foodbites.dto.response.JwtResponse;
import com.p2mw.foodbites.model.Admin;
import com.p2mw.foodbites.model.Seller;
import com.p2mw.foodbites.model.User;
import jakarta.servlet.http.HttpServletResponse;

public interface AuthService {
    JwtResponse authenticateUser(LoginRequest loginRequest, HttpServletResponse response);

    User registerUser(User user);

    Admin registerAdmin(Admin admin);

    Seller registerSeller(SellerSignupRequest sellerSignupRequest);
}
