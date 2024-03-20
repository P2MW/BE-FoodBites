package com.p2mw.foodbites.service;

import com.p2mw.foodbites.model.User;

public interface UserService {

    User getUserByEmail(String email);

    User createUser(User user);
    User updateUser(long id, User user);
}
