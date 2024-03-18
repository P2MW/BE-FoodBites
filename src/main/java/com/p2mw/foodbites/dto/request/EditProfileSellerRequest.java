package com.p2mw.foodbites.dto.request;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class EditProfileSellerRequest {
    private String sellerName;
    private String merchantName;
    private long categoryId;
    private String email;
    private String phoneNumber;
    private String password;
    private String address;
    private String orderingProcess;
    private MultipartFile profilePicture;
    private String description;
}
