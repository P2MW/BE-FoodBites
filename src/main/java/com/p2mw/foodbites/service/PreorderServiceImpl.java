package com.p2mw.foodbites.service;

import com.p2mw.foodbites.dto.request.PreorderRequest;
import com.p2mw.foodbites.dto.response.MenuResponse;
import com.p2mw.foodbites.model.Menu;
import com.p2mw.foodbites.repository.MenuRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class PreorderServiceImpl implements PreorderService {

    @Autowired
    private MenuRepository menuRepository;

    @Override
    public Map<MenuResponse, Integer> addMenuToCart(List<PreorderRequest> preorderList){
        Map<MenuResponse, Integer> cart = new HashMap<>();

        for (PreorderRequest item : preorderList){
            Menu menu = menuRepository.findById(item.getMenuId()).orElse(null);
            if (menu != null){
                MenuResponse menuResponse = new MenuResponse(menu);
                int quantity = item.getQuantity();
                cart.put(menuResponse, cart.getOrDefault(menuResponse, 0) + quantity);
            }
        }
        return cart;
    }

    @Override
    public double checkout(Map<MenuResponse, Integer> cart){
        double totalPrice = 0.0;
        for (Map.Entry<MenuResponse, Integer> entry : cart.entrySet()){
            MenuResponse menuResponse = entry.getKey();
            int quantity = entry.getValue();
            double price = menuResponse.getPrice();
            totalPrice += price * quantity;
        }
        return totalPrice;
    }
}
