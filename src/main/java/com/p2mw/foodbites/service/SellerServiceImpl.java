package com.p2mw.foodbites.service;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.p2mw.foodbites.dto.request.EditProfileSellerRequest;
import com.p2mw.foodbites.model.Category;
import com.p2mw.foodbites.model.Seller;
import com.p2mw.foodbites.repository.CategoryRepository;
import com.p2mw.foodbites.repository.SellerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.util.Map;

@Service
public class SellerServiceImpl implements SellerService{

    @Autowired
    private SellerRepository sellerRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private Cloudinary cloudinary;

    @Override
    public Seller findByEmail(String email) {
        return sellerRepository.findByEmail(email);
    }

    @Override
    public Seller findByMerchantName(String merchantName) {
        return sellerRepository.findByMerchantName(merchantName);
    }

    @Override
    public Seller createSeller(Seller seller) {
        return sellerRepository.save(seller);
    }

    @Override
    public Seller updateSeller(long id, EditProfileSellerRequest editProfileSellerRequest) {
        Seller updateSeller = sellerRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Seller not found"));
        Category category = categoryRepository.findById(editProfileSellerRequest.getCategoryId()).get();

        if (updateSeller != null){
            updateSeller.setSellerName(editProfileSellerRequest.getSellerName());
            updateSeller.setMerchantName(editProfileSellerRequest.getMerchantName());
            updateSeller.setCategory(category);
            updateSeller.setEmail(editProfileSellerRequest.getEmail());
            updateSeller.setPhoneNumber(editProfileSellerRequest.getPhoneNumber());
            updateSeller.setPassword(editProfileSellerRequest.getPassword());
            updateSeller.setAddress(editProfileSellerRequest.getAddress());
            updateSeller.setDescription(editProfileSellerRequest.getDescription());
            updateSeller.setOrderingProcess(editProfileSellerRequest.getOrderingProcess());

            if (editProfileSellerRequest.getProfilePicture() != null && !editProfileSellerRequest.getProfilePicture().isEmpty()) {
                try {
                    Map<?, ?> uploadResult = cloudinary.uploader().upload(
                            editProfileSellerRequest.getProfilePicture().getBytes(),
                            ObjectUtils.emptyMap());
                    String imageUrl = uploadResult.get("url").toString();
                    updateSeller.setProfilePicture(imageUrl);

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return sellerRepository.save(updateSeller);
    }
}
