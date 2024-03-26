package com.p2mw.foodbites.service;

import com.p2mw.foodbites.dto.request.PreorderRequest;
import com.p2mw.foodbites.dto.response.MenuResponse;

import java.util.List;
import java.util.Map;

public interface PreorderService {

    Map<MenuResponse, Integer> addMenuToCart(List<PreorderRequest> preorderList);

    double checkout(Map<MenuResponse, Integer> cart);
}
