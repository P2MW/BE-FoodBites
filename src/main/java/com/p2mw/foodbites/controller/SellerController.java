package com.p2mw.foodbites.controller;

import com.p2mw.foodbites.dto.request.EditProfileSellerRequest;
import com.p2mw.foodbites.dto.response.SellerResponse;
import com.p2mw.foodbites.model.Seller;
import com.p2mw.foodbites.service.SellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Stream;

@RestController
@RequestMapping("/api/seller")
public class SellerController {

    @Autowired
    private SellerService sellerService;

    @GetMapping
    public List<SellerResponse> getAllSeller(){
        return sellerService.getAllSeller();
    }

    @PutMapping("/edit-profile/")
    public ResponseEntity<Seller> updateSeller(@ModelAttribute EditProfileSellerRequest editProfileSellerRequest){
        Seller updatedSeller = sellerService.updateSeller(editProfileSellerRequest);
        if (updatedSeller != null){
            return ResponseEntity.ok(updatedSeller);
        } else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @GetMapping("/{id}")
    public Stream<SellerResponse> getSellerById(@PathVariable long id){
        return sellerService.getSellerById(id);
    }

    @GetMapping("/category/{categoryName}")
    public ResponseEntity<List<SellerResponse>> getSellerByCategoryName(@PathVariable String categoryName){
        List<SellerResponse> sellerResponseList = sellerService.getByCategoryName(categoryName);
        return ResponseEntity.ok(sellerResponseList);
    }
}
