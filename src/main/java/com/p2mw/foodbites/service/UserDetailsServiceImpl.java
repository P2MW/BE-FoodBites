package com.p2mw.foodbites.service;

import com.p2mw.foodbites.model.Admin;
import com.p2mw.foodbites.model.Seller;
import com.p2mw.foodbites.model.User;
import com.p2mw.foodbites.repository.AdminRepository;
import com.p2mw.foodbites.repository.SellerRepository;
import com.p2mw.foodbites.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private AdminRepository adminRepository;
    @Autowired
    private SellerRepository sellerRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email);
        if (user != null) {
            return UserDetailsImpl.buildUser(user);
        }

        Admin admin = adminRepository.findByEmail(email);
        if (admin != null) {
            return UserDetailsImpl.buildAdmin(admin);
        }

        Seller seller = sellerRepository.findByEmail(email);
        if (seller != null) {
            return UserDetailsImpl.buildSeller(seller);
        }

        throw new UsernameNotFoundException("User not found with email: " + email);
    }
}
