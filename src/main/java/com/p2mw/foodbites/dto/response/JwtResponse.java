package com.p2mw.foodbites.dto.response;


import lombok.Data;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

@Data
public class JwtResponse {
    private String token;
    private String type = "Bearer";
    private Long id;
    private String email;
    private Collection<? extends GrantedAuthority> authorities;

    public JwtResponse(String accessToken, Long id, String email, Collection<? extends GrantedAuthority> authorities) {
        this.token = accessToken;
        this.id = id;
        this.email = email;
        this.authorities = authorities;
    }

}
