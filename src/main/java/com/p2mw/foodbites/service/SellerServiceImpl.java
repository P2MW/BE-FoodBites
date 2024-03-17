package com.p2mw.foodbites.service;

import com.p2mw.foodbites.model.Seller;
import com.p2mw.foodbites.repository.SellerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SellerServiceImpl implements SellerService{

    @Autowired
    private SellerRepository sellerRepository;

    @Override
    public Seller findByMerchantName(String merchantName) {
        return sellerRepository.findByMerchantName(merchantName);
    }

    @Override
    public Seller save(Seller seller) {
        return sellerRepository.save(seller);
    }
}
