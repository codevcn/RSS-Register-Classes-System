package com.example.demo.controllers;

import com.example.demo.DTOs.response.AdminResDTO;
import com.example.demo.models.Admin;
import com.example.demo.services.AdminService;
import jakarta.servlet.http.HttpServletRequest;
import java.security.Principal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("admin")
@PreAuthorize("hasAuthority('ADMIN')")
public class AdminController {

    @Autowired
    private AdminService adminService;

    @GetMapping("get-admin-account-info")
    public ResponseEntity<AdminResDTO.GetAdminInfoResDTO> getAdminAccountInfo(
        @NonNull HttpServletRequest httpServletRequest
    ) {
        Admin admin = adminService.getAdminAccountInfo(httpServletRequest);
        return ResponseEntity.status(HttpStatus.OK).body(
            new AdminResDTO.GetAdminInfoResDTO(
                admin.getId(),
                admin.getIDCard(),
                admin.getFullName(),
                admin.getBirthday(),
                admin.getGender(),
                admin.getAccount().getUsername(),
                admin.getAccount().getRole().getRoleCode()
            )
        );
    }

    @GetMapping("get-admin/{accountID}")
    public ResponseEntity<AdminResDTO.GetAdminInfoResDTO> getAdminAccountInfo(
        @PathVariable("accountID") String accountID,
        Principal principal
    ) {
        String ID = principal.getName();
        System.out.println(ID);
        Admin admin = adminService.getAdminById(ID);
        AdminResDTO.GetAdminInfoResDTO adminInfoDTO = new AdminResDTO.GetAdminInfoResDTO(
            admin.getId(),
            admin.getIDCard(),
            admin.getFullName(),
            admin.getBirthday(),
            admin.getGender(),
            admin.getAccount().getUsername(),
            admin.getAccount().getRole().getRoleCode()
        );

        return ResponseEntity.ok(adminInfoDTO);
    }

    @PutMapping("update-admin/{id}")
    public ResponseEntity<AdminResDTO.GetAdminInfoResDTO> updateAdmin(
        @PathVariable("id") Long id,
        @RequestBody AdminResDTO.GetAdminInfoResDTO updatedAdminInfo
    ) {
        Admin updatedAdmin = adminService.updateAdmin(id, updatedAdminInfo);
        AdminResDTO.GetAdminInfoResDTO updatedAdminResponse = new AdminResDTO.GetAdminInfoResDTO(
            updatedAdmin.getId(),
            updatedAdmin.getIDCard(),
            updatedAdmin.getFullName(),
            updatedAdmin.getBirthday(),
            updatedAdmin.getGender(),
            updatedAdmin.getAccount().getUsername(),
            updatedAdmin.getAccount().getRole().getRoleCode()
        );
        return ResponseEntity.ok(updatedAdminResponse);
    }

    @PutMapping("Change-Password/{id}")
    public ResponseEntity<AdminResDTO.GetAdminInfoResDTO> ChangePassword(
        @PathVariable("id") Long id,
        @RequestBody AdminResDTO.GetAdminInfoResDTO updatedAdminInfo
    ) {
        Admin updatedAdmin = adminService.updateAdmin(id, updatedAdminInfo);
        AdminResDTO.GetAdminInfoResDTO updatedAdminResponse = new AdminResDTO.GetAdminInfoResDTO(
            updatedAdmin.getId(),
            updatedAdmin.getIDCard(),
            updatedAdmin.getFullName(),
            updatedAdmin.getBirthday(),
            updatedAdmin.getGender(),
            updatedAdmin.getAccount().getUsername(),
            updatedAdmin.getAccount().getRole().getRoleCode()
        );
        return ResponseEntity.ok(updatedAdminResponse);
    }
}
