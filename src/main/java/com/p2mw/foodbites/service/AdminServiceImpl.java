package com.p2mw.foodbites.service;

import com.p2mw.foodbites.model.Admin;
import com.p2mw.foodbites.repository.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminServiceImpl implements AdminService{

    @Autowired
    private AdminRepository adminRepository;

    @Override
    public Admin createAdmin(Admin admin) {
        return adminRepository.save(admin);
    }
}
