package com.example.demo.controllers;

import com.example.demo.DTOs.response.AccountResDTO;
import com.example.demo.DTOs.response.AccountResDTO.*;
import com.example.demo.DTOs.response.AdminResDTO;
import com.example.demo.models.Account;
import com.example.demo.models.Admin;
import com.example.demo.repositories.AccountRepository;
import com.example.demo.services.AccountService;
import com.example.demo.services.AdminService;
import com.example.demo.utils.messages.AuthMessage;
import com.example.demo.utils.exceptions.IncorrectPasswordException;
import java.security.Principal;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
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
    @Autowired
    private AccountService accountService;
    @Autowired
    private AccountRepository accountRepository;

    @GetMapping("get-admin")
    public ResponseEntity<AdminResDTO.GetAdminInfoResDTO> getAdminAccountInfo(
            Principal principal) {
        String username = principal.getName();
        Account adminAccount = accountService.getAccountInfo(username);
        Long id = adminAccount.getId();
        Admin admin = adminService.findAdminByAccountID(id);
        AdminResDTO.GetAdminInfoResDTO adminInfoDTO = new AdminResDTO.GetAdminInfoResDTO(
                admin.getId(),
                admin.getIDCard(),
                admin.getFullName(),
                admin.getBirthday(),
                admin.getGender(),
                admin.getAccount());
        return ResponseEntity.ok(adminInfoDTO);
    }

    @PutMapping("update-admin")
    public ResponseEntity<AdminResDTO.GetAdminInfoResDTO> updateAdmin(
            @RequestBody AdminResDTO.GetAdminInfoResDTO updatedAdminInfo) {
        String test = updatedAdminInfo.account().getUsername();
        System.out.println("????" + updatedAdminInfo);
        Long IDaccount = updatedAdminInfo.account().getId();
        try {
            if (accountService.check_username_exist(test, IDaccount)) {
                throw new IncorrectPasswordException(AuthMessage.USERNAME_EXITED);
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
        Admin updatedAdmin = adminService.updateAdmin(IDaccount, updatedAdminInfo);
        Account updateAccount = accountService.updateAccount(IDaccount, updatedAdminInfo.account());
        AdminResDTO.GetAdminInfoResDTO updatedAdminResponse = new AdminResDTO.GetAdminInfoResDTO(
                updatedAdmin.getId(),
                updatedAdmin.getIDCard(),
                updatedAdmin.getFullName(),
                updatedAdmin.getBirthday(),
                updatedAdmin.getGender(),
                updatedAdmin.getAccount());
        return ResponseEntity.ok(updatedAdminResponse);
    }

    @PutMapping("Change-Password")
    public ResponseEntity<GetAccountResDTO> ChangePassword(
            Principal principal,
            @RequestBody AccountResDTO.PassResDTO Pass)throws IncorrectPasswordException  {
        Account account = accountRepository.findByUsername(principal.getName());
        Long id = account.getId();
        String oldPass = Pass.oldPass();
        String newPass = Pass.newPass();
        if (!accountService.check_pass(id, oldPass)) {
            throw new IncorrectPasswordException(AuthMessage.INCORRECT_PASSWORD);
        } else {
            System.out.println(">>>" + accountService.check_pass(id, oldPass));
            accountService.changePass(id, newPass);
            return ResponseEntity.ok().build();
        }
    }

    @GetMapping("get-all-idcard")
    public ResponseEntity<List<String>> getAllIdcard() {
        List<String> idcard = accountRepository.findAllIdCards();
        return ResponseEntity.ok(idcard);
    }

}
