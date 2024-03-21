package com.p2mw.foodbites.repository;

import com.p2mw.foodbites.model.Menu;
import com.p2mw.foodbites.model.Seller;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MenuRepository extends JpaRepository<Menu, Long> {
    List<Menu> findBySeller(Seller seller);
}
