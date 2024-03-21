package com.p2mw.foodbites.dto.response;

import com.p2mw.foodbites.model.Menu;
import lombok.Data;

@Data
public class MenuResponse {
    private long id;
    private String menuName;
    private double price;
    private String description;
    private String image;
    private String minimumOrder;
    private String merchantName;

    public MenuResponse(Menu menu) {
        this.id = menu.getId();
        this.menuName = menu.getMenuName();
        this.price = menu.getPrice();
        this.description = menu.getDescription();
        this.image = menu.getImage();
        this.minimumOrder = menu.getMinimumOrder();
        this.merchantName = menu.getSeller().getMerchantName();
    }
}

