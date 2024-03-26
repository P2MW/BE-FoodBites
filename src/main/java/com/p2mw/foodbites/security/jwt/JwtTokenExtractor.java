package com.p2mw.foodbites.security.jwt;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

@Component
public class JwtTokenExtractor {

    @Autowired
    private JwtUtils jwtUtils;

    public String extractJwtTokenFromCookie(HttpServletRequest request) {
        String jwtToken = null;
        String cookieHeader = request.getHeader(HttpHeaders.COOKIE);
        if (StringUtils.hasText(cookieHeader)) {
            String[] cookies = cookieHeader.split(";");
            for (String cookie : cookies) {
                String[] parts = cookie.split("=");
                if (parts.length == 2 && parts[0].trim().equals("JWT_TOKEN")) {
                    jwtToken = parts[1].trim();
                    break;
                }
            }
        }
        if (jwtToken == null) {
            throw new RuntimeException("JWT_TOKEN cookie not found");
        }
        return jwtToken;
    }

    public String getEmailFromJwtToken(String jwtToken) {
        String username = jwtUtils.getUsernameFromJwtToken(jwtToken);
        return username;
    }
}


