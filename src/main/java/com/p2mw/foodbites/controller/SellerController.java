package com.p2mw.foodbites.controller;

import com.p2mw.foodbites.dto.request.EditProfileSellerRequest;
import com.p2mw.foodbites.model.Seller;
import com.p2mw.foodbites.service.SellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/seller")
public class SellerController {

    @Autowired
    private SellerService sellerService;

    @PutMapping("/update/{id}")
    public ResponseEntity<Seller> updateSeller(@PathVariable long id, @ModelAttribute EditProfileSellerRequest editProfileSellerRequest){
        Seller updatedSeller = sellerService.updateSeller(id, editProfileSellerRequest);
        if (updatedSeller != null){
            return ResponseEntity.ok(updatedSeller);
        } else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }
}
