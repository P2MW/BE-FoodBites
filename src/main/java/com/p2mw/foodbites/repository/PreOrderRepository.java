package com.p2mw.foodbites.repository;

import com.p2mw.foodbites.model.PreOrder;
import com.p2mw.foodbites.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PreOrderRepository extends JpaRepository<PreOrder, Long> {
    List<PreOrder> findByUser(User user);
    List<PreOrder> findByProcessed(boolean processed);
}

