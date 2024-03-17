package com.p2mw.foodbites.service;

import com.p2mw.foodbites.model.User;

public interface UserService {

    User findByFullName(String fullName);

    User save(User user);
}
