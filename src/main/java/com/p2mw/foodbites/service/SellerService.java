package com.p2mw.foodbites.service;

import com.p2mw.foodbites.model.Seller;

public interface SellerService {

    Seller findByMerchantName(String merchantName);

    Seller save(Seller seller);
}
