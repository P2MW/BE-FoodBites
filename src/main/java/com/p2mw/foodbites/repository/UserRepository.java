package com.p2mw.foodbites.repository;

import com.p2mw.foodbites.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByFullName(String fullName);
}
