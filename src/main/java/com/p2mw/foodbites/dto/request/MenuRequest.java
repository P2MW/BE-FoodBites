package com.p2mw.foodbites.dto.request;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class MenuRequest {
    private long id;
    private String menuName;
    private double price;
    private String description;
    private MultipartFile image;
    private String minimumOrder;
    private long sellerId;
}
