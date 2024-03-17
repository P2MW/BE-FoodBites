package com.p2mw.foodbites.model;

import com.p2mw.foodbites.enumeration.Category;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    private double price;
    private String description;
    private String image;

    @ManyToOne
    @JoinColumn(referencedColumnName = "id", nullable = false)
    private Seller seller;
}
