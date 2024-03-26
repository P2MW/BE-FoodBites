package com.p2mw.foodbites.service;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.p2mw.foodbites.dto.request.LoginRequest;
import com.p2mw.foodbites.dto.request.SellerSignupRequest;
import com.p2mw.foodbites.dto.response.JwtResponse;
import com.p2mw.foodbites.model.Admin;
import com.p2mw.foodbites.model.Category;
import com.p2mw.foodbites.model.Seller;
import com.p2mw.foodbites.model.User;
import com.p2mw.foodbites.repository.AdminRepository;
import com.p2mw.foodbites.repository.CategoryRepository;
import com.p2mw.foodbites.repository.SellerRepository;
import com.p2mw.foodbites.repository.UserRepository;
import com.p2mw.foodbites.security.jwt.JwtUtils;
import jakarta.servlet.http.HttpServletResponse;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.util.Map;

@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserService userService;

    @Autowired
    private SellerService sellerService;

    @Autowired
    private AdminService adminService;

    @Autowired
    private JwtUtils jwtUtils;

    @Autowired
    private Cloudinary cloudinary;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private PasswordEncoder encoder;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AdminRepository adminRepository;

    @Autowired
    private SellerRepository sellerRepository;

    @Override
    public JwtResponse authenticateUser(LoginRequest loginRequest, HttpServletResponse response) {
        String email = loginRequest.getEmail();

        // Cari pengguna berdasarkan email
        User user = userRepository.findByEmail(email);
        if (user != null) {
            return authenticateAndGenerateJwt(user.getEmail(), loginRequest.getPassword(), response, UserDetailsImpl.buildUser(user));
        } else {
            // Jika tidak ditemukan, coba mencari sebagai admin
            Admin admin = adminRepository.findByEmail(email);
            if (admin != null) {
                return authenticateAndGenerateJwt(admin.getEmail(), loginRequest.getPassword(), response, UserDetailsImpl.buildAdmin(admin));
            }
            // Jika tidak ditemukan lagi, coba mencari sebagai seller
            Seller seller = sellerRepository.findByEmail(email);
            if (seller != null) {
                return authenticateAndGenerateJwt(seller.getEmail(), loginRequest.getPassword(), response, UserDetailsImpl.buildSeller(seller));
            }
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No user, admin, or seller found with the given email");
        }
    }

    private JwtResponse authenticateAndGenerateJwt(String email, String password, HttpServletResponse response, UserDetailsImpl userDetails) {
        // Otentikasi pengguna
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(email, password));
        SecurityContextHolder.getContext().setAuthentication(authentication);

        // Buat token JWT
        String jwt = jwtUtils.generateJwtToken(authentication);

        // Set cookie JWT di header respons
        ResponseCookie jwtCookie = ResponseCookie.from("JWT_TOKEN", jwt)
                .httpOnly(true)
                .secure(true)
                .path("/")
                .sameSite("None")
                .build();
        response.addHeader(HttpHeaders.SET_COOKIE, jwtCookie.toString());

        // Buat dan kembalikan respons JWT
        return new JwtResponse(jwt, userDetails.getId(), userDetails.getEmail(), userDetails.getAuthorities());
    }


    @Override
    public User registerUser(User user) {
        if (userService.getUserByEmail(user.getEmail()) != null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Error: email is already taken!");
        }
        user.setPassword(encoder.encode(user.getPassword()));
        userService.createUser(user);
        return user;
    }

    @Override
    public Admin registerAdmin(Admin admin) {
        admin.setPassword(encoder.encode(admin.getPassword()));
        adminService.createAdmin(admin);
        return admin;
    }

    @Override
    public Seller registerSeller(SellerSignupRequest sellerSignupRequest) {
        Seller seller = modelMapper.map(sellerSignupRequest, Seller.class);
        Category category = categoryRepository.findById(sellerSignupRequest.getCategoryId()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Category not found"));

        if (sellerService.findByEmail(sellerSignupRequest.getEmail()) != null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Error: email is already taken!");
        }

        if (sellerService.findByMerchantName(sellerSignupRequest.getMerchantName()) != null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Error: merchantName is already taken!");
        }

        if (sellerSignupRequest.getProfilePicture() != null && !sellerSignupRequest.getProfilePicture().isEmpty()) {
            try {
                Map<?, ?> uploadResult = cloudinary.uploader().upload(
                        sellerSignupRequest.getProfilePicture().getBytes(),
                        ObjectUtils.emptyMap());
                String imageUrl = uploadResult.get("url").toString();
                seller.setProfilePicture(imageUrl);

            } catch (IOException e) {
                throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error uploading profile picture", e);
            }
        }

        seller.setPassword(encoder.encode(seller.getPassword()));
        seller.setCategory(category);
        sellerService.createSeller(seller);
        return seller;
    }
}