package com.p2mw.foodbites.service;

import com.p2mw.foodbites.dto.request.EditProfileSellerRequest;
import com.p2mw.foodbites.dto.response.SellerResponse;
import com.p2mw.foodbites.model.Seller;

import java.util.List;
import java.util.stream.Stream;

public interface SellerService {

    Seller findByEmail(String email);
    Seller findByMerchantName(String merchantName);
    Seller createSeller(Seller seller);
    Seller updateSeller(long id, EditProfileSellerRequest editProfileSellerRequest);
    List<SellerResponse> getAllSeller();
    Stream<SellerResponse> getSellerById(long id);
    List<SellerResponse> getByCategoryName(String categoryName);
}
