package com.p2mw.foodbites.controller;

import com.p2mw.foodbites.dto.request.PreorderRequest;
import com.p2mw.foodbites.dto.response.MenuResponse;
import com.p2mw.foodbites.service.PreorderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/preorder")
public class PreorderController {

    @Autowired
    private PreorderService preorderService;

    @PostMapping("add-to-cart")
    public ResponseEntity<Map<MenuResponse, Integer>> addToCart(@RequestBody List<PreorderRequest> preorderList) {
        Map<MenuResponse, Integer> cart = preorderService.addMenuToCart(preorderList);
        return ResponseEntity.ok(cart);
    }

    @PostMapping("/checkout")
    public ResponseEntity<Double> checkout(@RequestBody List<PreorderRequest> preorderList){
        Map<MenuResponse, Integer> cart = preorderService.addMenuToCart(preorderList);
        double totalPrice = preorderService.checkout(cart);
        return ResponseEntity.ok(totalPrice);
    }
}
