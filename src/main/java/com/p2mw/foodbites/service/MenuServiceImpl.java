package com.p2mw.foodbites.service;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.p2mw.foodbites.dto.request.MenuRequest;
import com.p2mw.foodbites.dto.response.MenuResponse;
import com.p2mw.foodbites.model.Menu;
import com.p2mw.foodbites.repository.MenuRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MenuServiceImpl implements MenuService{

    @Autowired
    private MenuRepository menuRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private Cloudinary cloudinary;

    @Override
    public List<MenuResponse> getAllMenu() {
        List<Menu> menus = menuRepository.findAll();
        return menus.stream()
                .map(MenuResponse::new)
                .collect(Collectors.toList());
    }

    @Override
    public MenuResponse addMenu(MenuRequest menuRequest) {
        Menu menu = modelMapper.map(menuRequest, Menu.class);
        if (menuRequest.getImage() != null && !menuRequest.getImage().isEmpty()) {
            try {
                Map<?, ?> uploadResult = cloudinary.uploader().upload(
                        menuRequest.getImage().getBytes(),
                        ObjectUtils.emptyMap());
                String imageUrl = uploadResult.get("url").toString();
                menu.setImage(imageUrl);

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        menu = menuRepository.save(menu);
        return new MenuResponse(menu);
    }

    @Override
    public MenuResponse updateMenu(long id, MenuRequest menuRequest) {
        Menu updatedMenu = menuRepository.findById(id)
                .orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "Menu Not Found!"));

        updatedMenu.setMenuName(menuRequest.getMenuName());
        updatedMenu.setDescription(menuRequest.getDescription());
        updatedMenu.setPrice(menuRequest.getPrice());
        updatedMenu.setMinimumOrder(menuRequest.getMinimumOrder());
        if (menuRequest.getImage() != null && !menuRequest.getImage().isEmpty()) {
            try {
                Map<?, ?> uploadResult = cloudinary.uploader().upload(
                        menuRequest.getImage().getBytes(),
                        ObjectUtils.emptyMap());
                String imageUrl = uploadResult.get("url").toString();
                updatedMenu.setImage(imageUrl);

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        updatedMenu = menuRepository.save(updatedMenu);
        return new MenuResponse(updatedMenu);
    }

    @Override
    public void deleteMenu(long id) {
        Menu deletedMenu = menuRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Menu Not Found"));
        menuRepository.delete(deletedMenu);

    }

    @Override
    public List<MenuResponse> getMenuByMenuName(String menuName) {
        return menuRepository.findByMenuName(menuName);
    }
}
