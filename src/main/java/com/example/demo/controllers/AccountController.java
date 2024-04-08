package com.example.demo.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.DTOs.response.AccountResDTO.GetAllusernameResDTO;
import com.example.demo.DTOs.response.SubjectResDTO.GetSubjectResDTO;
import com.example.demo.models.Account;
import com.example.demo.models.Subject;
import com.example.demo.repositories.AccountRepository;
import com.example.demo.services.AccountService;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("account")
public class AccountController {

    @Autowired
    private AccountRepository accountRepository;
    @GetMapping("get-all-username")
      public ResponseEntity<List<GetAllusernameResDTO>> getAllusername() {
        List<Account> allAccountUsername = accountRepository.findAll();
        List<Account> ListAllusername = new ArrayList<>();

        for (Account account : allAccountUsername) {
            if (account.getDeleted() == false) {
                ListAllusername.add(account);
            }
        }
        List<GetAllusernameResDTO> usernameList = ListAllusername
            .stream()
            .map(
                account ->
                    new GetAllusernameResDTO(
                        account.getId(),
                        account.getUsername()
                    )
            )
            .collect(Collectors.toList());
        return ResponseEntity.ok(usernameList);
    }
    
}
