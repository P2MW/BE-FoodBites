package com.p2mw.foodbites.repository;

import com.p2mw.foodbites.dto.response.MenuResponse;
import com.p2mw.foodbites.model.Menu;
import com.p2mw.foodbites.model.Seller;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MenuRepository extends JpaRepository<Menu, Long> {
    @Query("SELECT m FROM Menu m WHERE LOWER(m.menuName) LIKE LOWER(CONCAT('%', :menuName, '%'))")
    List<MenuResponse> findByMenuName(String menuName);
}
