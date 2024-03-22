package com.p2mw.foodbites.dto.response;

import com.p2mw.foodbites.enumeration.ECategory;
import com.p2mw.foodbites.model.Category;
import com.p2mw.foodbites.model.Seller;
import lombok.Data;

@Data
public class SellerResponse {
    private String sellerName;
    private String merchantName;
    private ECategory categoryName;
    private String phoneNumber;
    private String address;
    private String orderingProcess;
    private String profilePicture;
    private String description;

    public SellerResponse(Seller seller) {
        this.sellerName = seller.getSellerName();
        this.merchantName = seller.getMerchantName();
        this.categoryName = seller.getCategory().getName();
        this.phoneNumber = seller.getPhoneNumber();
        this.address = seller.getAddress();
        this.orderingProcess = seller.getOrderingProcess();
        this.profilePicture = seller.getProfilePicture();
        this.description = seller.getDescription();
    }
}
