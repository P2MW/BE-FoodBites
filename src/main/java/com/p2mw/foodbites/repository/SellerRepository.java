package com.p2mw.foodbites.repository;

import com.p2mw.foodbites.dto.response.SellerResponse;
import com.p2mw.foodbites.enumeration.ECategory;
import com.p2mw.foodbites.model.Seller;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SellerRepository extends JpaRepository<Seller, Long> {

    Seller findByEmail(String email);
    Seller findByMerchantName(String merchantName);

    @Query("SELECT s FROM Seller s WHERE s.category.name = :categoryName")
    List<SellerResponse> findByCategory(@Param("categoryName")ECategory categoryName);
}
