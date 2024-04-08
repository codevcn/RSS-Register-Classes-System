package com.example.demo.services;
import com.example.demo.models.Account;
import com.example.demo.repositories.AccountRepository;
import java.sql.Timestamp;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.password.PasswordEncoder;
@Service
public class AccountService {
    @Autowired
    private PasswordEncoder passwordEncoder;
    
    @Autowired
    private AccountRepository accountRepository;

    public Account getAccountInfo(String username) {
        return accountRepository.findByUsername(username);
    }

    public boolean check_pass(Long id, String oldpass) { 
        Account account = accountRepository.findByID(id); 
        return passwordEncoder.matches(oldpass, account.getPassword());
    }
    

    public boolean check_username_exist(String username, Long id) {
        Account currentUsername = accountRepository.findAllbyID(id);
        List<Account> accountUsers = accountRepository.findAll();
        for (Account account : accountUsers) {
            if (account.getUsername().equals(username) && !account.getUsername().equals(currentUsername.getUsername())) {
                return true;
            }
        }
        return false; 
    }
    
    public Account updateAccount(Long IDaccount,Account updateAccount){
        Account account = accountRepository.findByID(IDaccount);
        account.setId(updateAccount.getId());
        account.setUsername(updateAccount.getUsername());
        account.setPassword(updateAccount.getPassword());
        account.setUpdatedAt(new Timestamp(System.currentTimeMillis()));
        account.setDeleted(updateAccount.getDeleted());
        return accountRepository.save(account);
    }

    public Account changePass(Long id,String newPass){
        Account account = accountRepository.findByID(id);
        account.setPassword(encodePassword(newPass));
        account.setUpdatedAt(new Timestamp(System.currentTimeMillis()));
        return accountRepository.save(account);
    }
    
    private String encodePassword(String original_password) {
        return passwordEncoder.encode(original_password);
    }
}
