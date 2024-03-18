package com.p2mw.foodbites.dto.request;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

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
    private MultipartFile profilePicture;
    private String description;
}
