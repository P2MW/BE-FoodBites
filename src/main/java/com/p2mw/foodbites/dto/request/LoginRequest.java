package com.p2mw.foodbites.dto.request;

import lombok.Data;

@Data
public class LoginRequest {
    private String email;
    private String password;
}
