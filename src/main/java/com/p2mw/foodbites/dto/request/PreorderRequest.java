package com.p2mw.foodbites.dto.request;

import lombok.Data;

@Data
public class PreorderRequest {

    private long menuId;
    private int quantity;
}
