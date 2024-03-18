package com.p2mw.foodbites.dto.request;

import lombok.Data;

import java.util.Set;

@Data
public class SellerSignupRequest {
    private long id;

    private String sellerName;
    private String merchantName;
    private long categoryId;
    private String email;
    private String password;
    private String phoneNumber;
    private String address;
    private String orderingProcess;
    private String profilePicture;
    private String description;
}
