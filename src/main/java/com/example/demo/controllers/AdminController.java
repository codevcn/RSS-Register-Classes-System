package com.example.demo.controllers;

import com.example.demo.DTOs.response.AdminResDTOs;
import com.example.demo.DTOs.response.AdminResDTOs.*;
import com.example.demo.models.Admin;
import com.example.demo.services.AdminService;
import jakarta.servlet.http.HttpServletRequest;
import java.security.Principal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("admin")
public class AdminController {

    @Autowired
    private AdminService adminService;

    @GetMapping("get-admin-account-info")
    public ResponseEntity<GetAdminInfoResDTO> getAdminAccountInfo(
        @NonNull HttpServletRequest httpServletRequest
    ) {
        Admin admin = adminService.getAdminAccountInfo(httpServletRequest);
        return ResponseEntity.status(HttpStatus.OK).body(
            new GetAdminInfoResDTO(
                admin.getId(),
                admin.getIdcard(),
                admin.getFullName(),
                admin.getBirthday(),
                admin.getGender(),
                admin.getAccount().getUsername(),
                admin.getAccount().getRole().getId()
            )
        );
    }

    // @GetMapping("get-admin")
    // public ResponseEntity<GetAdminInfoResDTO> getAdminAccountInfo(
    // Principal principal) {
    // String adminId = principal.getName();
    // Admin admin = adminService.getAdminById(adminId);
    // GetAdminInfoResDTO adminInfoDTO = new GetAdminInfoResDTO(
    // admin.getId(),
    // admin.getIdcard(),
    // admin.getFullName(),
    // admin.getBirthday(),
    // admin.getGender(),
    // admin.getAccount().getUsername(),
    // admin.getAccount().getRole().getId());

    // return ResponseEntity.ok(adminInfoDTO);
    // }

    @GetMapping("get-admin/{accountID}")
    public ResponseEntity<GetAdminInfoResDTO> getAdminAccountInfo(
        @PathVariable("accountID") String accountID,
        Principal principal
    ) {
        String ID = principal.getName();
        System.out.println(ID);
        Admin admin = adminService.getAdminById(ID);
        GetAdminInfoResDTO adminInfoDTO = new GetAdminInfoResDTO(
            admin.getId(),
            admin.getIdcard(),
            admin.getFullName(),
            admin.getBirthday(),
            admin.getGender(),
            admin.getAccount().getUsername(),
            admin.getAccount().getRole().getId()
        );

        return ResponseEntity.ok(adminInfoDTO);
    }

    @PutMapping("update-admin/{id}")
    public ResponseEntity<GetAdminInfoResDTO> updateAdmin(
        @PathVariable("id") Integer id,
        @RequestBody AdminResDTOs.GetAdminInfoResDTO updatedAdminInfo
    ) {
        Admin updatedAdmin = adminService.updateAdmin(id, updatedAdminInfo);
        GetAdminInfoResDTO updatedAdminResponse = new GetAdminInfoResDTO(
            updatedAdmin.getId(),
            updatedAdmin.getIdcard(),
            updatedAdmin.getFullName(),
            updatedAdmin.getBirthday(),
            updatedAdmin.getGender(),
            updatedAdmin.getAccount().getUsername(),
            updatedAdmin.getAccount().getRole().getId()
        );
        return ResponseEntity.ok(updatedAdminResponse);
    }

    @PutMapping("Change-Password/{id}")
    public ResponseEntity<GetAdminInfoResDTO> ChangePassword(
        @PathVariable("id") Integer id,
        @RequestBody AdminResDTOs.GetAdminInfoResDTO updatedAdminInfo
    ) {
        Admin updatedAdmin = adminService.updateAdmin(id, updatedAdminInfo);
        GetAdminInfoResDTO updatedAdminResponse = new GetAdminInfoResDTO(
            updatedAdmin.getId(),
            updatedAdmin.getIdcard(),
            updatedAdmin.getFullName(),
            updatedAdmin.getBirthday(),
            updatedAdmin.getGender(),
            updatedAdmin.getAccount().getUsername(),
            updatedAdmin.getAccount().getRole().getId()
        );
        return ResponseEntity.ok(updatedAdminResponse);
    }
}
