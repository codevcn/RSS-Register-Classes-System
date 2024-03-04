package com.example.demo.services;

import com.example.demo.DTOs.response.AdminResDTOs;
import com.example.demo.models.Admin;
import com.example.demo.repositories.AdminRepository;
import jakarta.servlet.http.HttpServletRequest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

@Service
public class AdminService {
    @Autowired
    private AdminRepository adminRepository;

    public Admin getAdminInfo(@NonNull HttpServletRequest httpServletRequest) {
        String username = httpServletRequest.getUserPrincipal().getName();
        return adminRepository.findByAccountUsername(username);
    }
    
    public AdminService(AdminRepository adminRepository) {
        this.adminRepository = adminRepository;
    }

    public List<Admin> getAllAdmins() {
        return adminRepository.findAll();
    }

    public Admin getAdminById(String id) {
        return adminRepository.findById(id).orElse(null);
    }

    public Admin createAdmin(Admin admin) {
        return adminRepository.save(admin);
    }

    public Admin updateAdmin(String id, Admin admin) {
        if (adminRepository.existsById(id)) {
            admin.setIdcard(id);
            return adminRepository.save(admin);
        }
        return null;
    }

    public void deleteAdmin(String id) {
        adminRepository.deleteById(id);
    }


}
