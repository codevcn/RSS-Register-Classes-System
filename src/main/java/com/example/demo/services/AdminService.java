package com.example.demo.services;

import com.example.demo.DTOs.response.AdminResDTO;
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

    public Admin getAdminAccountInfo(@NonNull HttpServletRequest httpServletRequest) {
        String username = httpServletRequest.getUserPrincipal().getName();
        return adminRepository.findByAccountUsername(username);
    }

    public AdminService(AdminRepository adminRepository) {
        this.adminRepository = adminRepository;
    }

    public List<Admin> getAllAdmins() {
        return (List<Admin>) adminRepository.findAll();
    }

    public Admin getAdminById(String accountID) {
        return adminRepository.findByAccountUsername(accountID);
    }

    public Admin createAdmin(Admin admin) {
        return adminRepository.save(admin);
    }

    public Admin updateAdmin(Long id, AdminResDTO.GetAdminInfoResDTO updatedAdminInfo) {
        Admin admin = adminRepository.findByID(id);
        admin.setIDCard(updatedAdminInfo.idcard());
        admin.setFullName(updatedAdminInfo.fullName());
        admin.setBirthday(updatedAdminInfo.birthday());
        admin.setGender(updatedAdminInfo.gender());
        return adminRepository.save(admin);
    }

    public Admin findAdminByAccountID(Long accountID) {
        return adminRepository.findByAccountID(accountID);
    }
}
