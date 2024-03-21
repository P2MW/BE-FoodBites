package com.p2mw.foodbites.controller;

import com.p2mw.foodbites.dto.request.MenuRequest;
import com.p2mw.foodbites.dto.response.MenuResponse;
import com.p2mw.foodbites.dto.response.MessageResponse;
import com.p2mw.foodbites.model.Menu;
import com.p2mw.foodbites.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/menu")
public class MenuController {

    @Autowired
    private MenuService menuService;

    @GetMapping
    public List<MenuResponse> getAllMenu(){
        return menuService.getAllMenu();
    }

    @PostMapping("/add")
    public MenuResponse addMenu(@ModelAttribute MenuRequest menuRequest){
        return menuService.addMenu(menuRequest);
    }

    @PutMapping("/update/{id}")
    public MenuResponse updateMenu(@PathVariable long id, @ModelAttribute MenuRequest menuRequest){
        return menuService.updateMenu(id, menuRequest);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteMenu(@PathVariable long id){
        menuService.deleteMenu(id);
        return ResponseEntity.ok(new MessageResponse("Menu has been successfully deleted"));
    }
}
