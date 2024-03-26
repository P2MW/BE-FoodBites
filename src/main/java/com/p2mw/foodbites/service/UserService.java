package com.p2mw.foodbites.service;

import com.p2mw.foodbites.model.User;

import java.util.Optional;

public interface UserService {

    User getUserByEmail(String email);

    User createUser(User user);
    User updateUser(User user);
}
