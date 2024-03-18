package com.p2mw.foodbites.service;

import com.p2mw.foodbites.model.Admin;
import com.p2mw.foodbites.model.Seller;
import com.p2mw.foodbites.model.User;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Data
public class UserDetailsImpl implements UserDetails {
    private static final long serialVersionUID = 1L;

    private long id;

    private String sellerName;

    private String merchantName;

    private String fullName;

    private String email;

    private String phoneNumber;

    @JsonIgnore
    private String password;

    private Collection<? extends GrantedAuthority> authorities;

    // Constructor for Admin
    public UserDetailsImpl(Long id, String email, String password,
                           Collection<? extends GrantedAuthority> authorities) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.authorities = authorities;
    }

    // Constructor for User
    public UserDetailsImpl(long id, String fullName, String email, String phoneNumber, String password,
                           Collection<? extends GrantedAuthority> authorities) {
        this.id = id;
        this.fullName = fullName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.password = password;
        this.authorities = authorities;
    }

    // Constructor for Seller
    public UserDetailsImpl(long id, String sellerName, String merchantName, String email, String phoneNumber, String password,
                           Collection<? extends GrantedAuthority> authorities) {
        this.id = id;
        this.sellerName = sellerName;
        this.merchantName = merchantName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.password = password;
        this.authorities = authorities;
    }


    public static UserDetailsImpl buildAdmin(Admin admin) {
        List<GrantedAuthority> authorities = List.of(new SimpleGrantedAuthority("ROLE_ADMIN"));

        return new UserDetailsImpl(
                admin.getId(),
                admin.getEmail(),
                admin.getPassword(),
                authorities);
    }

    public static UserDetailsImpl buildUser(User user) {
        List<GrantedAuthority> authorities = List.of(new SimpleGrantedAuthority("ROLE_USER"));

        return new UserDetailsImpl(
                user.getId(),
                user.getFullName(),
                user.getEmail(),
                user.getPhoneNumber(),
                user.getPassword(),
                authorities);
    }

    public static UserDetailsImpl buildSeller(Seller seller) {
        List<GrantedAuthority> authorities = List.of(new SimpleGrantedAuthority("ROLE_SELLER"));

        return new UserDetailsImpl(
                seller.getId(),
                seller.getSellerName(),
                seller.getEmail(),
                seller.getPhoneNumber(),
                seller.getPassword(),
                authorities);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        if (fullName != null) {
            return fullName;
        } else if (sellerName != null) {
            return sellerName;
        } else {
            return email;
        }
    }


    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        UserDetailsImpl user = (UserDetailsImpl) o;
        return Objects.equals(id, user.id);
    }
}

