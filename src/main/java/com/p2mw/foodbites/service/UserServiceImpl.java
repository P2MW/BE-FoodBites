package com.p2mw.foodbites.service;

import com.p2mw.foodbites.model.User;
import com.p2mw.foodbites.repository.UserRepository;
import com.p2mw.foodbites.security.jwt.JwtTokenExtractor;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder encoder;

    @Autowired
    private JwtTokenExtractor jwtTokenExtractor;

    @Override
    public User getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public User createUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public User updateUser(User user) {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();

        // Membaca token dari cookie
        String jwtToken = jwtTokenExtractor.extractJwtTokenFromCookie(request);

        // Validasi token, lalu ambil email pengguna dari token
        String userEmail = jwtTokenExtractor.getEmailFromJwtToken(jwtToken);

        User updatedUser = userRepository.findByEmail(userEmail);
        if (updatedUser == null) {
            throw new RuntimeException("User not found");
        }

        updatedUser.setFullName(user.getFullName());
        updatedUser.setPhoneNumber(user.getPhoneNumber());
        updatedUser.setPassword(encoder.encode(user.getPassword()));
        updatedUser.setCity(user.getCity());

        return userRepository.save(updatedUser);
    }
}
