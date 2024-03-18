package com.p2mw.foodbites.service;

import com.p2mw.foodbites.dto.request.EditProfileSellerRequest;
import com.p2mw.foodbites.model.Seller;

public interface SellerService {

    Seller findByEmail(String email);
    Seller findByMerchantName(String merchantName);
    Seller createSeller(Seller seller);
    Seller updateSeller(long id, EditProfileSellerRequest editProfileSellerRequest);
}
