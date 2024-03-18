package com.p2mw.foodbites.model;

import com.p2mw.foodbites.enumeration.ECategory;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Seller {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String sellerName;
    private String merchantName;

    @ManyToOne
    private Category category;

    private String email;
    private String password;
    private String phoneNumber;
    private String address;
    private String orderingProcess;
    private String profilePicture;
    private String description;
}
