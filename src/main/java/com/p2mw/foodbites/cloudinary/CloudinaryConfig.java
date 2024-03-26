package com.p2mw.foodbites.cloudinary;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.p2mw.foodbites.service.CloudinaryService;
import com.p2mw.foodbites.service.CloudinaryServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@RequiredArgsConstructor
@Configuration
public class CloudinaryConfig {

    @Bean
    public Cloudinary cloudinary() {
        return new Cloudinary(ObjectUtils.asMap(
                "cloud_name", "foodbites",
                "api_key", "814597556887254",
                "api_secret", "wZ87ErxTBNE0Y1hN8DbBOSHyhKo"));
    }

    @Bean
    public CloudinaryService cloudinaryService(){
        return new CloudinaryServiceImpl(cloudinary());
    }
}
