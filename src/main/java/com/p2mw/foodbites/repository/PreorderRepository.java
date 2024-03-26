package com.p2mw.foodbites.repository;

import com.p2mw.foodbites.model.Preorder;
import com.p2mw.foodbites.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PreorderRepository extends JpaRepository<Preorder, Long> {
    List<Preorder> findByUser(User user);
    List<Preorder> findByProcessed(boolean processed);
}

