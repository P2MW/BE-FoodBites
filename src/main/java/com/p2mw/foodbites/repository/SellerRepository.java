package com.p2mw.foodbites.repository;

import com.p2mw.foodbites.model.Seller;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SellerRepository extends JpaRepository<Seller, Long> {

    Seller findByEmail(String email);
    Seller findByMerchantName(String merchantName);
}
