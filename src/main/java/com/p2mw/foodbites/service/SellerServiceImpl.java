package com.p2mw.foodbites.service;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.p2mw.foodbites.dto.request.EditProfileSellerRequest;
import com.p2mw.foodbites.dto.response.SellerResponse;
import com.p2mw.foodbites.enumeration.ECategory;
import com.p2mw.foodbites.model.Category;
import com.p2mw.foodbites.model.Seller;
import com.p2mw.foodbites.repository.CategoryRepository;
import com.p2mw.foodbites.repository.SellerRepository;
import com.p2mw.foodbites.security.jwt.JwtTokenExtractor;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class SellerServiceImpl implements SellerService{

    @Autowired
    private SellerRepository sellerRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private Cloudinary cloudinary;

    @Autowired
    private PasswordEncoder endcoder;

    @Autowired
    private JwtTokenExtractor jwtTokenExtractor;

    @Override
    public Optional<Seller> findById(long id) {
        return sellerRepository.findById(id);
    }

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
    public Seller updateSeller(EditProfileSellerRequest editProfileSellerRequest) {

        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();

        // Membaca token dari cookie
        String jwtToken = jwtTokenExtractor.extractJwtTokenFromCookie(request);

        // Validasi token, lalu ambil email pengguna dari token
        String sellerEmail = jwtTokenExtractor.getEmailFromJwtToken(jwtToken);


        Seller updateSeller = sellerRepository.findByEmail(sellerEmail);
        if (updateSeller == null) {
            throw new RuntimeException("Seller not found");
        }

        Category category = categoryRepository.findById(editProfileSellerRequest.getCategoryId()).get();

        if (updateSeller != null){
            updateSeller.setSellerName(editProfileSellerRequest.getSellerName());
            updateSeller.setMerchantName(editProfileSellerRequest.getMerchantName());
            updateSeller.setCategory(category);
            updateSeller.setEmail(editProfileSellerRequest.getEmail());
            updateSeller.setPhoneNumber(editProfileSellerRequest.getPhoneNumber());
            updateSeller.setPassword(endcoder.encode(editProfileSellerRequest.getPassword()));
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

    @Override
    public List<SellerResponse> getAllSeller() {
        List<Seller> sellers = sellerRepository.findAll();
        return sellers.stream()
                .map(SellerResponse::new)
                .collect(Collectors.toList());
    }

    @Override
    public Stream<SellerResponse> getSellerById(long id) {
        Optional<Seller> seller = sellerRepository.findById(id);
        return seller.stream()
                .map(SellerResponse::new);
    }

    @Override
    public List<SellerResponse> getByCategoryName(String categoryName) {
        return sellerRepository.findByCategory(ECategory.valueOf(categoryName.toUpperCase()));
    }
}
