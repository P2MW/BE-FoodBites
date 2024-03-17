package com.p2mw.foodbites.service;

import com.p2mw.foodbites.model.User;
import com.p2mw.foodbites.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User findByFullName(String fullName) {
        return userRepository.findByFullName(fullName);
    }

    @Override
    public User save(User user) {
        return userRepository.save(user);
    }
}
