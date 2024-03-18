package com.p2mw.foodbites.controller;

import com.p2mw.foodbites.dto.request.LoginRequest;
import com.p2mw.foodbites.dto.request.SellerSignupRequest;
import com.p2mw.foodbites.dto.response.JwtResponse;
import com.p2mw.foodbites.dto.response.MessageResponse;
import com.p2mw.foodbites.enumeration.ECategory;
import com.p2mw.foodbites.model.Seller;
import com.p2mw.foodbites.model.Category;
import com.p2mw.foodbites.model.User;
import com.p2mw.foodbites.repository.CategoryRepository;
import com.p2mw.foodbites.security.jwt.JwtUtils;
import com.p2mw.foodbites.service.AdminService;
import com.p2mw.foodbites.service.SellerService;
import com.p2mw.foodbites.service.UserDetailsImpl;
import com.p2mw.foodbites.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.Set;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserService userService;

    @Autowired
    private SellerService sellerService;

    @Autowired
    private AdminService adminService;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private JwtUtils jwtUtils;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private PasswordEncoder encoder;

    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@RequestBody LoginRequest loginRequest) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();

        return ResponseEntity.ok(new JwtResponse(jwt, userDetails.getId(), userDetails.getEmail(), userDetails.getAuthorities()));
    }

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@RequestBody User user) {
        if (userService.getUserByEmail(user.getEmail()) != null) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: email is already taken!"));
        }
        user.setPassword(encoder.encode(user.getPassword()));
        userService.createUser(user);

        return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
    }

    @PostMapping("/signup/seller")
    public ResponseEntity<?> registerSeller(@RequestBody SellerSignupRequest sellerSignupRequest) {
        Seller seller = modelMapper.map(sellerSignupRequest, Seller.class);
        Category category = categoryRepository.findById(sellerSignupRequest.getCategoryId()).get();

        if (sellerService.findByEmail(sellerSignupRequest.getEmail()) != null) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: email is already taken!"));
        }

        if (sellerService.findByMerchantName(sellerSignupRequest.getMerchantName()) != null) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: merchantName is already taken!"));
        }

        seller.setPassword(encoder.encode(seller.getPassword()));
        seller.setCategory(category);
        sellerService.createSeller(seller);

        return ResponseEntity.ok(new MessageResponse("Seller registered successfully!"));
    }

}
