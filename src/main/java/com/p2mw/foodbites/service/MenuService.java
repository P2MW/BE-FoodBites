package com.p2mw.foodbites.service;

import com.p2mw.foodbites.dto.request.MenuRequest;
import com.p2mw.foodbites.dto.response.MenuResponse;
import com.p2mw.foodbites.model.Menu;

import java.util.List;
import java.util.Optional;

public interface MenuService {
    List<MenuResponse> getAllMenu();
    MenuResponse addMenu(MenuRequest menuRequest);
    MenuResponse updateMenu(long id, MenuRequest menuRequest);
    void deleteMenu(long id);
}
