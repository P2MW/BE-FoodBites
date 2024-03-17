package com.p2mw.foodbites.model;

import com.p2mw.foodbites.enumeration.Category;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Seller {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String merchantName;
    private Category category;
    private String email;
    private String password;
    private String phoneNumber;
    private String address;
    private String orderingProcess;
    private String profilePicture;
    private String description;
}
