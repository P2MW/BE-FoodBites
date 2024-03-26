package com.p2mw.foodbites.controller;

import com.p2mw.foodbites.dto.request.LoginRequest;
import com.p2mw.foodbites.dto.request.SellerSignupRequest;
import com.p2mw.foodbites.dto.response.JwtResponse;
import com.p2mw.foodbites.model.Admin;
import com.p2mw.foodbites.model.Seller;
import com.p2mw.foodbites.model.User;
import com.p2mw.foodbites.service.AuthService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/signin")
    public ResponseEntity<JwtResponse> authenticateUser(@RequestBody LoginRequest loginRequest, HttpServletResponse response) {
        JwtResponse jwtResponse = authService.authenticateUser(loginRequest, response);
        return ResponseEntity.ok(jwtResponse);
    }

    @PostMapping("/signup/user")
    public ResponseEntity<User> registerUser(@RequestBody User user) {
        User registeredUser = authService.registerUser(user);
        return ResponseEntity.ok(registeredUser);
    }

    @PostMapping("/signup/admin")
    public ResponseEntity<Admin> registerAdmin(@RequestBody Admin admin) {
        Admin registeredAdmin = authService.registerAdmin(admin);
        return ResponseEntity.ok(registeredAdmin);
    }

    @PostMapping("/signup/seller")
    public ResponseEntity<Seller> registerSeller(@ModelAttribute SellerSignupRequest sellerSignupRequest) {
        Seller registeredSeller = authService.registerSeller(sellerSignupRequest);
        return ResponseEntity.ok(registeredSeller);
    }
}


