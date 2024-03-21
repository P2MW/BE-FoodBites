package com.p2mw.foodbites.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Menu {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String menuName;
    private double price;
    private String description;
    private String image;
    private String minimumOrder;

    @ManyToOne
    @JoinColumn(referencedColumnName = "id", nullable = false)
    private Seller seller;
}
