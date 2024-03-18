package com.p2mw.foodbites.service;

import org.springframework.web.multipart.MultipartFile;

public interface CloudinaryService {
    String upload(MultipartFile multipartFile);
}
