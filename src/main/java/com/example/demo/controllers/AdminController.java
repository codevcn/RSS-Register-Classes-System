package com.example.demo.controllers;

import com.example.demo.DTOs.response.AdminResDTOs;
import com.example.demo.DTOs.response.AdminResDTOs.*;
import com.example.demo.DTOs.request.AdminReqDTOs.*;
import com.example.demo.models.Admin;
import com.example.demo.services.AdminService;

import jakarta.servlet.http.HttpServletRequest;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;



@RestController
@RequestMapping("admin")
public class AdminController {

    @Autowired
    private AdminService adminService;

    @GetMapping("get-admin-info")
    public ResponseEntity<GetAdminInfoResDTO> getAdminInfo(
        @NonNull HttpServletRequest httpServletRequest
    ) {
        Admin admin = adminService.getAdminInfo(httpServletRequest);
        return ResponseEntity
            .status(HttpStatus.OK)
            .body(
                new GetAdminInfoResDTO(
                    admin.getIdcard(),
                    admin.getFullName(),
                    admin.getBirthday(),
                    admin.getGender(),
                    admin.getAccount().getUsername(),
                    admin.getAccount().getRole().getId()
                )
            );
    }

    @GetMapping("get-all-admin")
    public ResponseEntity<List<GetAdminInfoResDTO>> getAllAdmins() {
        List<Admin> admins = adminService.getAllAdmins();
        List<GetAdminInfoResDTO> adminInfoList = admins.stream()
                .map(admin -> new GetAdminInfoResDTO(
                        admin.getIdcard(),
                        admin.getFullName(),
                        admin.getBirthday(),
                        admin.getGender(),
                        admin.getAccount().getUsername(),
                        admin.getAccount().getRole().getId()
                ))
                .collect(Collectors.toList());
        return ResponseEntity.ok(adminInfoList);
    }
    
    @PostMapping("create-admin")
    public ResponseEntity<GetAdminInfoResDTO> createAdmin(@RequestBody CreateAdminReqDTO createAdminReqDTO) {
        Admin admin = new Admin();

        admin.setIdcard(createAdminReqDTO.getIdcard());
        admin.setFullName(createAdminReqDTO.getFullName());
        admin.setBirthday(createAdminReqDTO.getBirthday());
        admin.setGender(createAdminReqDTO.getGender());


        Admin createdAdmin = adminService.createAdmin(admin);

 
        GetAdminInfoResDTO createdAdminInfo = new GetAdminInfoResDTO(
                createdAdmin.getIdcard(),
                createdAdmin.getFullName(),
                createdAdmin.getBirthday(),
                createdAdmin.getGender(),
                createdAdmin.getAccount().getUsername(),
                createdAdmin.getAccount().getRole().getId()
        );
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(createdAdminInfo);
    }

    @PutMapping("update-admin/{id}")
    public ResponseEntity<GetAdminInfoResDTO> updateAdmin(
            @PathVariable("id") String id,
            @RequestBody UpdateAdminReqDTO updateAdminReqDTO
    ) {
        Admin existingAdmin = adminService.getAdminById(id);
        if (existingAdmin != null) {
            // Cập nhật thông tin admin từ request body
            existingAdmin.setIdcard(updateAdminReqDTO.getIdcard());
            existingAdmin.setFullName(updateAdminReqDTO.getFullName());
            existingAdmin.setBirthday(updateAdminReqDTO.getBirthday());
            existingAdmin.setGender(updateAdminReqDTO.getGender());

            // Lưu admin đã cập nhật vào cơ sở dữ liệu
            Admin updatedAdmin = adminService.updateAdmin(id, existingAdmin);

            // Trả về thông tin của admin đã cập nhật
            GetAdminInfoResDTO updatedAdminInfo = new GetAdminInfoResDTO(
                    updatedAdmin.getIdcard(),
                    updatedAdmin.getFullName(),
                    updatedAdmin.getBirthday(),
                    updatedAdmin.getGender(),
                    updatedAdmin.getAccount().getUsername(),
                    updatedAdmin.getAccount().getRole().getId()
            );
            return ResponseEntity.ok(updatedAdminInfo);
        } else {
            // Trả về lỗi 404 nếu không tìm thấy admin
            return ResponseEntity.notFound().build();
        }
        }

        @DeleteMapping("delete-admin/{id}")
        public ResponseEntity<Void> deleteAdmin(
                @PathVariable("id") String id
        ) {
            adminService.deleteAdmin(id);
            return ResponseEntity.noContent().build();
        }

}